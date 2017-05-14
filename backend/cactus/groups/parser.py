import requests
from .models import *

API_DOMAIN = 'https://api.rozklad.hub.kpi.ua'

def create_lessons(group, group_id):
    lessons = []
    response = requests.get(API_DOMAIN + '/lessons/?limit=100&groups='+str(group_id)).json()
    lessons.extend(response['results'])

    while response['next']:
        response = requests.get(response['next']).json()
        lessons.extend(response['results'])

    for lesson in lessons:
        if lesson['week'] == 1:
            continue

        print('passing lesson', lesson)

        subject, _ = Subject.objects.get_or_create(
            group=group,
            name=lesson['discipline_name'],
            teacher=(lesson['teachers_short_names'] or [' ',])[0]
        )

        Lesson.objects.create(
            subject=subject,
            day=lesson['day'],
            week=lesson['week'],
            number_of_lesson=lesson['number'],
            description=', '.join(lesson['teachers_short_names'])
        )







def create_groups():
    results = []
    response = requests.get(API_DOMAIN + '/groups/?limit=1').json()

    results.extend(response['results'])

    for result in results:
        print('start', result['name'], result['id'])
        gr, _ = Group.objects.get_or_create(
            name=result['name'],
        )
        gr.save()
        create_lessons(gr, result['id'])
        print('end', result['name'])

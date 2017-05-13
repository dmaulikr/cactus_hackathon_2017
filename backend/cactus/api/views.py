import json
from rest_framework.generics import *
from django.http.response import JsonResponse
from rest_framework.response import Response
from rest_framework.decorators import api_view
from groups.models import *

from .serializers import *


@api_view(["GET"])
def timeline_view(request):

    tasks = Task.objects.all()
    tasks_json = TaskSerializer(tasks, many=True).data


    group = Group.objects.last()
    print(group)
    lessons_json = LessonSerializer(group.get_lessons(), many=True).data if group else []
    return Response(lessons_json + tasks_json)


class TaskCreateView(CreateAPIView):
    queryset = Task.objects.all()
    serializer_class = TaskSerializer

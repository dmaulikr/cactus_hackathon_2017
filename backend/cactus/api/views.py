import json
from rest_framework.generics import *
from django.http.response import JsonResponse
from rest_framework.response import Response
from rest_framework.decorators import api_view
from groups.models import *

from .serializers import *


@api_view(["GET"])
def timeline_view(request):
    lessons = Lesson.objects.all()
    tasks = Task.objects.all()

    lessons_json = LessonSerializer(lessons, many=True).data
    tasks_json = TaskSerializer(tasks, many=True).data
    return Response(lessons_json + tasks_json)


class TaskCreateView(CreateAPIView):
    queryset = Task.objects.all()
    serializer_class = TaskSerializer

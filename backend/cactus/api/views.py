import json
from rest_framework.generics import *
from rest_framework.response import Response
from rest_framework.decorators import api_view
from groups.models import *

from .serializers import *


@api_view(["GET"])
def timeline_view(request):

    tasks = Task.objects.filter(group=None)
    tasks_json = TaskSerializer(tasks, many=True).data


    group = Group.objects.first()

    lessons_json = LessonSerializer(group.get_lessons(), many=True).data

    result = lessons_json + tasks_json
    result.sort(
        key=lambda x: x['timestamp']
    )
    return Response(result)


class TaskCreateView(CreateAPIView):
    queryset = Task.objects.all()
    serializer_class = TaskSerializer


class SubjectListView(ListAPIView):
    queryset = Subject.objects.all()
    serializer_class = SubjectSerializer


class GroupTaskCreateView(CreateAPIView):
    queryset = Task.objects.all()
    serializer_class = TaskSerializer

    def perform_create(self, serializer):
        ins = serializer.save()
        ins.group = Group.objects.first()
        ins.save()
        return ins


class GroupTaskListView(ListAPIView):
    queryset = Task.objects.filter(group__isnull=False)
    serializer_class = TaskSerializer

import json
from rest_framework.generics import ListAPIView
from django.http.response import JsonResponse, HttpResponse
from rest_framework.decorators import api_view
from groups.models import *

from .serializers import *


class TimelineView(ListAPIView):
    queryset = Lesson.objects.all()
    serializer_class = LessonSerializer


@api_view(["GET"])
def timeline_view(request):
    lessons = 123
    response_data = LessonSerializer(lessons, many=True).data
    print(response_data, type(response_data))
    return JsonResponse({})
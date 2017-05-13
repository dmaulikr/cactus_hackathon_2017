from rest_framework import serializers
from groups.models import *



class LessonSerializer(serializers.Serializer):
    def to_representation(self, instance):
        return {
            "title": instance.subject.name,
            "description": str(instance.description),
            "project_id": instance.subject.id,
            "time": 'sample time',
            "timestamp": 0.0,
        }


class TaskSerializer(serializers.ModelSerializer):
    class Meta:
        model = Task
        fields = [
            'photo_url',
            'description',
            'title'
        ]

    def to_representation(self, instance):
        return {
            "title": instance.title,
            "description": str(instance.description),
            "project_id": 1,
            "time": 'sample time',
            "timestamp": 0.0,
        }
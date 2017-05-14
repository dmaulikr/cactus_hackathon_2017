from rest_framework import serializers
from groups.models import *



class LessonSerializer(serializers.Serializer):
    def to_representation(self, instance):
        return {
            "title": instance.subject.name,
            "description": str(instance.description),
            "project_id": instance.subject.id,
            "time": instance.datetime.strftime("%A %H:%M"),
            "timestamp": instance.datetime.timestamp(),
            'photo_url': "null",
        }


class TaskSerializer(serializers.ModelSerializer):
    class Meta:
        model = Task
        fields = [
            'photo_url',
            'description',
            'title',
            'stamp',
        ]

    def to_representation(self, instance):
        return {
            "id": instance.id,
            "title": instance.title,
            "description": str(instance.description),
            "project_id": instance.group.id if instance.group else 0,
            "time": instance.datetime.strftime("%A %H:%M"),
            "timestamp": instance.datetime.timestamp(),
            'photo_url': instance.photo_url,
        }


class SubjectSerializer(serializers.ModelSerializer):
    class Meta:
        model = Subject
        fields = [
            'id',
            'name',
            'teacher',
        ]

    def to_representation(self, subject):
        return {
            'id': subject.id,
            'name': subject.name,
            'teacher': subject.teacher,
            'lessons_visited': subject.lessons_visited,
            'labs_passed': subject.labs_passed,
            'fire_status': subject.fire_status,
        }


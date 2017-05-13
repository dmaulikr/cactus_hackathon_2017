from rest_framework import serializers



class LessonSerializer(serializers.Serializer):
    def to_representation(self, instance):
        return {
            "title": instance.subject.name,
            "description": str(instance.description),
            "project_id": instance.subject.id,
            "time": 'sample time',
            "timestamp": 0.0,
        }
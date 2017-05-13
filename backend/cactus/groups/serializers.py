from rest_framework import serializers
from time import time
from django.utils.crypto import get_random_string
from .models import *


class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = [
            'name',
            'email',
            'password',
        ]

    def save(self, **kwargs):

        instance = super().save(**kwargs)
        instance.token = get_random_string(20)
        instance.save()
        return instance
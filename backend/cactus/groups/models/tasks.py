import datetime
from django.db import models
from .groups import *


class Task(models.Model):
    title = models.CharField(
        max_length=100,
        verbose_name='Title',
    )
    description = models.TextField(
        verbose_name='Description',
    )
    time = models.DateTimeField(
        verbose_name='Time',
        null=True,
        blank=True,
    )
    stamp = models.FloatField(
        default=1494719436.0
    )
    photo_url = models.URLField(
        verbose_name='Image',
        null=True,
        blank=True
    )
    group = models.ForeignKey(
        Group,
        null=True,
        blank=True,
    )

    @property
    def datetime(self):
        return self.time

    class Meta:
        verbose_name = 'Task'
        verbose_name_plural = 'Tasks'

    def save(self, *args, **kwargs):
        if self.time is None:
            self.time = datetime.datetime.fromtimestamp(self.stamp)
        return super().save(*args, **kwargs)

    def __str__(self):
        return self.title

    def __repr__(self):
        return self.__str__()

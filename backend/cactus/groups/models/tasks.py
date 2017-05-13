from django.db import models
from django.contrib.postgres.fields import JSONField


class Task(models.Model):
    title = models.CharField(
        max_length=100,
        verbose_name='Title',
    )
    description = JSONField(
        verbose_name='Description',
    )
    time = models.DateTimeField(
        verbose_name='Time',
    )
    photo_url = models.URLField(
        verbose_name='Image',
        null=True,
        blank=True
    )

    class Meta:
        verbose_name = 'Task'
        verbose_name_plural = 'Tasks'



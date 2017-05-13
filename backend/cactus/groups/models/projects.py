from django.db import models
from django.contrib.postgres.fields import ArrayField
from .users import *


class Project(models.Model):
    group = models.ForeignKey(
        Group,
        related_name='projects'
    )
    title = models.CharField(
        max_length=32,
        verbose_name='Title',
    )

    class Meta:
        verbose_name = 'UserProject'
        verbose_name_plural = 'UserProjects'

    def __str__(self):
        return 'Project \"{}\" of {}'.format(self.title, str(self.owner))

    def __repr__(self):
        return self.__str__()


class Task(models.Model):
    project = models.ForeignKey(
        Project,
        verbose_name='Project'
    )
    title = models.CharField(
        max_length=100,
    )
    description = models.TextField(
        default='No description provided',
    )
    REPEAT_CHOICES = (
        ('week', 'Week'),
        ('day', 'Day'),
        ('month', 'Month'),
    )
    repeat = models.BooleanField(
        default=False,
    )
    repeat_after = models.IntegerField(
        null=True,
        blank=True,
    )
    repeat_time = models.CharField(
        max_length=10,
        choice=REPEAT_CHOICES,
        null=True,
        blank=True,
    )
    attachments = ArrayField(
        base_field=models.TextField,
        verbose_name='attachments',
    )

    @property
    def short_description(self):
        return self.description[:100]

    class Meta:
        verbose_name = 'Task'
        verbose_name_plural = 'Tasks'

    def __str__(self):
        return 'Task \"{}\" of {}'.format(self.title, self.project)

    def __repr__(self):
        return self.__str__()

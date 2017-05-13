import datetime
from django.db import models
from django.contrib.postgres.fields import JSONField


class Group(models.Model):
    name = models.CharField(
        max_length=64,
        verbose_name='Name'
    )

    def __str__(self):
        return self.name

    def __repr__(self):
        return self.__str__()


class Subject(models.Model):
    group = models.ForeignKey(
        Group,
        verbose_name='Group',
        related_name='subjects'
    )
    name = models.CharField(
        max_length=64,
        verbose_name='Name',
    )
    teacher = models.CharField(
        max_length=64,
        verbose_name='Teacher`s name'
    )

    def __str__(self):
        return "{} <{}>".format(self.name, self.group.name)

    def __repr__(self):
        return self.__str__()


class Lesson(models.Model):
    subject = models.ForeignKey(
        Subject,
        verbose_name='Subject',
        related_name='lessons'
    )
    DAY_CHOICES = (
        (1, 'Monday'),
        (2, 'Tuesday'),
        (3, 'Wednesday'),
        (4, 'Thursday'),
        (5, 'Friday'),
        (6, 'Saturday'),
        (7, 'Sunday'),
    )
    day = models.IntegerField(
        max_length=16,
        choices=DAY_CHOICES,
        verbose_name='Day of the week',
    )
    WEEK_CHOICES = (
        (1, 'First week'),
        (2, 'Second week'),
    )
    week = models.PositiveSmallIntegerField(
        choices=WEEK_CHOICES,
        verbose_name='Number of week',
    )
    NUMBER_OF_LESSON_CHOICES = (
        (1, 'First'),
        (2, 'Second'),
        (3, 'Third'),
        (4, 'Fourth'),
        (5, 'Fifth'),
    )
    lessons_times = {
        1: ('8:30', '10:05'),
        2: ('10:25', '12:00'),
        3: ('12:20', '13:55'),
        4: ('14:15', '15:50'),
        5: ('16:10', '17:45'),
    }
    number_of_lesson = models.SmallIntegerField(
        choices=NUMBER_OF_LESSON_CHOICES,
        verbose_name="Number of lessons",
    )
    description = JSONField(
        verbose_name='Description',
    )

    def get_next_lessons(self, time_from=None):
        if not isinstance(time_from, datetime.datetime):
            time_from = datetime.datetime.now()

        now = datetime.datetime.now()
        next = datetime.datetime(
            year=now.year,
            month=now.year,
            hour=
        )





    def __str__(self):
        return "{} <{}>, {} lesson on {}".format(
            self.subject.name, self.subject.group.name,
            self.number_of_lesson, self.day
        )

    def __repr__(self):
        return self.__str__()
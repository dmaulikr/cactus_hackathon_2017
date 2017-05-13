from django.db import models


class User(models.Model):
    email = models.EmailField(
        verbose_name="Email",
        unique=True,
    )
    name = models.CharField(
        max_length=32,
        verbose_name="name",
    )
    password = models.CharField(
        max_length=32,
        verbose_name='Password',
    )
    token = models.CharField(
        max_length=32,
        null=True,
        blank=True,
    )

    class Meta:
        verbose_name = 'User'
        verbose_name_plural = 'Users'


class Group(models.Model):
    name = models.CharField(
        max_length=32,
        verbose_name="name",
    )
    password = models.CharField(
        max_length=32,
        verbose_name='Password',
    )
    token = models.CharField(
        max_length=32,
        null=True,
        blank=True,
    )

    class Meta:
        verbose_name = 'Group'
        verbose_name_plural = 'Groups'

from django.db import models
from .groups import Group


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
    # group = models.ForeignKey(
    #     Group,
    #     verbose_name='Group',
    #     related_name='students'
    # )

    class Meta:
        verbose_name = 'User'
        verbose_name_plural = 'Users'

    def __str__(self):
        return 'User \"{}\" <{}>'.format(self.name, str(self.email))

    def __repr__(self):
        return self.__str__()

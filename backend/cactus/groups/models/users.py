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
    basic_group = models.ForeignKey(
        'groups.Group',
        related_name='user_based_on',
        null=True,
        blank=True,
    )
    groups = models.ManyToManyField(
        'groups.Group',
        related_name='users',
    )

    def save(self, *args, **kwargs):
        if self.basic_group is None:
            new_group = Group(
                name='MyGroup',
                # :D
                password='000000000000000000000000000000',
                group_type='user',
            )
            new_group.save()
            self.basic_group = new_group
        return super().save(*args, **kwargs)

    class Meta:
        verbose_name = 'User'
        verbose_name_plural = 'Users'

    def __str__(self):
        return 'User \"{}\" <{}>'.format(self.name, str(self.email))

    def __repr__(self):
        return self.__str__()


class Group(models.Model):
    TYPE_CHOICES = (
        ('user', 'Personal user`s group'),
        ('public', 'Public group'),
    )
    group_type = models.CharField(
        max_length=10,
        choices=TYPE_CHOICES,
        default='user',
    )
    name = models.CharField(
        max_length=32,
        verbose_name="name",
        unique=True,
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

    def __str__(self):
        return 'Group \"{}\"'.format(self.name)

    def __repr__(self):
        return self.__str__()

    class Meta:
        verbose_name = 'Group'
        verbose_name_plural = 'Groups'

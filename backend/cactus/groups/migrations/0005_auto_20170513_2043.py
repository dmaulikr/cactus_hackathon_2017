# -*- coding: utf-8 -*-
# Generated by Django 1.11.1 on 2017-05-13 20:43
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('groups', '0004_task'),
    ]

    operations = [
        migrations.AlterField(
            model_name='task',
            name='description',
            field=models.TextField(verbose_name='Description'),
        ),
    ]

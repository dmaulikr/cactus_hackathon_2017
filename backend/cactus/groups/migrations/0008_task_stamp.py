# -*- coding: utf-8 -*-
# Generated by Django 1.11.1 on 2017-05-13 23:37
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('groups', '0007_auto_20170513_2256'),
    ]

    operations = [
        migrations.AddField(
            model_name='task',
            name='stamp',
            field=models.FloatField(default=1494719436.0),
        ),
    ]

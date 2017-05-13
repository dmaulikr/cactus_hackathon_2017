# -*- coding: utf-8 -*-
# Generated by Django 1.11.1 on 2017-05-13 20:05
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('groups', '0002_auto_20170513_1759'),
    ]

    operations = [
        migrations.AlterField(
            model_name='lesson',
            name='week',
            field=models.PositiveSmallIntegerField(choices=[(0, 'Every week'), (1, 'First week'), (2, 'Second week')], verbose_name='Number of week'),
        ),
    ]
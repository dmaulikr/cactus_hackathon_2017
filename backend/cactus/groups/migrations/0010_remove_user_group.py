# -*- coding: utf-8 -*-
# Generated by Django 1.11.1 on 2017-05-14 05:34
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('groups', '0009_auto_20170514_0206'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='user',
            name='group',
        ),
    ]
# -*- coding: utf-8 -*-
# Generated by Django 1.11.1 on 2017-05-14 06:10
from __future__ import unicode_literals

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('groups', '0010_remove_user_group'),
    ]

    operations = [
        migrations.AddField(
            model_name='task',
            name='group',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='groups.Group'),
        ),
    ]
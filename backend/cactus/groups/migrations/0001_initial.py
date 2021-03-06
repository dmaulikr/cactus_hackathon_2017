# -*- coding: utf-8 -*-
# Generated by Django 1.11.1 on 2017-05-13 17:56
from __future__ import unicode_literals

import django.contrib.postgres.fields.jsonb
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Group',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=64, verbose_name='Name')),
            ],
        ),
        migrations.CreateModel(
            name='Lesson',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('day', models.CharField(choices=[(1, 'Monday'), (2, 'Tuesday'), (3, 'Wednesday'), (4, 'Thursday'), (5, 'Friday'), (6, 'Saturday'), (7, 'Sunday')], max_length=16, verbose_name='Day of the week')),
                ('week', models.PositiveSmallIntegerField(choices=[(1, 'First week'), (2, 'Second week')], verbose_name='Number of week')),
                ('number_of_lesson', models.SmallIntegerField(choices=[(1, 'First'), (2, 'Second'), (3, 'Third'), (4, 'Fourth'), (5, 'Fifth')], verbose_name=((1, 'First'), (2, 'Second'), (3, 'Third'), (4, 'Fourth'), (5, 'Fifth')))),
                ('description', django.contrib.postgres.fields.jsonb.JSONField(verbose_name='Description')),
            ],
        ),
        migrations.CreateModel(
            name='Subject',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=64, verbose_name='Name')),
                ('teacher', models.CharField(max_length=64, verbose_name='Teacher`s name')),
                ('group', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='subjects', to='groups.Group', verbose_name='Group')),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('email', models.EmailField(max_length=254, unique=True, verbose_name='Email')),
                ('name', models.CharField(max_length=32, verbose_name='name')),
                ('password', models.CharField(max_length=32, verbose_name='Password')),
                ('token', models.CharField(blank=True, max_length=32, null=True)),
                ('group', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='students', to='groups.Group', verbose_name='Group')),
            ],
            options={
                'verbose_name': 'User',
                'verbose_name_plural': 'Users',
            },
        ),
        migrations.AddField(
            model_name='lesson',
            name='subject',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='lessons', to='groups.Subject', verbose_name='Subject'),
        ),
    ]

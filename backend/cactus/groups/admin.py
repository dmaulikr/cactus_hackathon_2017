from django.contrib import admin
from .models import *


admin.site.register([User, Group, Subject, Lesson, Task])

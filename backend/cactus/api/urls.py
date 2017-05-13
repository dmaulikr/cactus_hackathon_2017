from django.conf.urls import url
from .views import *

urlpatterns = [
    url(r'^timeline/$', timeline_view),
    url(r'^tasks/add/$', TaskCreateView.as_view()),
    url(r'subjects/', SubjectListView.as_view()),
]

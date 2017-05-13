from django.conf.urls import url
from .views import *

urlpatterns = [
    #url(r'^timeline/$', timeline_view),
    url(r'^timeline/$', TimelineView.as_view()),
    url(r'^tasks/add/$', TaskCreateView.as_view()),
]

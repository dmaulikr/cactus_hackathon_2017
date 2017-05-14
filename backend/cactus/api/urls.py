from django.conf.urls import url
from .views import *

urlpatterns = [
    url(r'^timeline/$', timeline_view),
    url(r'^tasks/add/$', TaskCreateView.as_view()),
    url(r'^subjects/$', SubjectListView.as_view()),

    url(r'group/tasks/$', GroupTaskListView.as_view()),
    url(r'group/tasks/add/$', GroupTaskCreateView.as_view()),

    url(r'subjects/(?P<pk>\d+)/$', SubjectRetrieveView.as_view()),
#    url(r'subjects/status/$', SubjectRetrieveView),
]

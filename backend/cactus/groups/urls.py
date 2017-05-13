from django.conf.urls import url
from .views import *


urlpatterns = [
    url(r'^register/', RegisterUser.as_view(), name='user-register'),
    url(r'^login/', login_view, name='user-login'),

    # url(r'^groups/add/', login_view, name='user-login'),
    # url(r'^groups/connect/', login_view, name='user-login'),
]

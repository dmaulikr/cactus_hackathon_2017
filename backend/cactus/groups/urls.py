from django.conf.urls import url
from .views import *


urlpatterns = [
    url(r'^register/', RegisterUser.as_view(), name='user-register'),
    url(r'^login/', login_view, name='user-login'),
]

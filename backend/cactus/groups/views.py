from rest_framework.generics import *
from rest_framework.decorators import api_view
from django.http.response import JsonResponse
from django.shortcuts import get_object_or_404
from .models import *
from .serializers import *



class RegisterUser(CreateAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer


@api_view(http_method_names=['POST', ])
def login_view(request):
    user = get_object_or_404(User,
                             password=request.data.get('password'),
                             email=request.data.get('email'))

    return JsonResponse(data={
        'token': user.token,
        'name': user.name,

    })

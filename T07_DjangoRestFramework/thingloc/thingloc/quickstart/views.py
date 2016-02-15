from django.shortcuts import render
from rest_framework import viewsets
from thingloc.quickstart.serializers import *

# Create your views here.
class UsuarioViewSet(viewsets.ModelViewSet):
    queryset = Usuario.objects.all().order_by('username')
    serializer_class = UsuarioSerializer

class ObjetosViewSet(viewsets.ModelViewSet):
    queryset = Objetos.objects.all().order_by('nombre')
    serializer_class = ObjetosSerializer

class CategoriaViewSet(viewsets.ModelViewSet):
    queryset = Categoria.objects.all().order_by('nombre')
    serializer_class = CategoriaSerializer

class MensajesViewSet(viewsets.ModelViewSet):
    queryset = Mensajes.objects.all().order_by('fecha')
    serializer_class = MensajesSerializer


"""
class UserViewSet(viewsets.ModelViewSet):

    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer


class GroupViewSet(viewsets.ModelViewSet):

    queryset = Group.objects.all()
    serializer_class = GroupSerializer
"""
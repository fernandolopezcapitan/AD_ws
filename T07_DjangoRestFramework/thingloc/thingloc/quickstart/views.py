from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.decorators import list_route
from rest_framework.response import Response
from thingloc.quickstart.serializers import *

# Create your views here.
# class UsuarioViewSet(viewsets.ModelViewSet):
#     queryset = Usuario.objects.all().order_by('username')
#     serializer_class = UsuarioSerializer
class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer

class ObjetoViewSet(viewsets.ModelViewSet):
    queryset = Objeto.objects.all().order_by('nombre')
    serializer_class = ObjetoSerializer

    @list_route(methods=['GET'])
    def me(self, request):
        qs = Objeto.objects.filter(usuario=self.request.user).order_by('-perdido')
        serializer = self.get_serializer(qs, many=True)
        return Response(serializer.data)

class CategoriaViewSet(viewsets.ModelViewSet):
    queryset = Categoria.objects.all().order_by('nombre')
    serializer_class = CategoriaSerializer

class MensajeViewSet(viewsets.ModelViewSet):
    queryset = Mensaje.objects.all().order_by('fecha')
    serializer_class = MensajeSerializer
    search_fields = ('id',)
    filter_fields = ('objeto',)


"""
class UserViewSet(viewsets.ModelViewSet):

    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer


class GroupViewSet(viewsets.ModelViewSet):

    queryset = Group.objects.all()
    serializer_class = GroupSerializer
"""
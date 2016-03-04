# -*- coding: utf-8 -*-
from django.contrib.auth.models import User, Group
from thingloc.quickstart.models import Objeto, Categoria, Mensaje
from rest_framework import serializers
__author__ = 'flopez'

class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('id','username','password','first_name','last_name','email')


class GroupSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Group
        fields = ('url', 'name')


"""
El serializador de Usuarios
"""

# class UsuarioSerializer(serializers.HyperlinkedModelSerializer):
#     class Meta:
#         model = Usuario
#         fields = ('id','username','password','first_name','last_name','email')

"""
El serializador de Objetos
"""

class ObjetoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Objeto
        fields = ('id','nombre','recompensa','perdido','foto','coordenadas','categoria','usuario')


"""
El serializador de Categoría
"""

class CategoriaSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Categoria
        fields = ('id','nombre')

"""
El serializador de Mensajes
"""

class MensajeSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Mensaje
        fields = ('id','usuarioEmisor','usuarioReceptor','objeto','comentario','fecha')



# class UserSerializer(serializers.HyperlinkedModelSerializer):
#     class Meta:
#         model = User
#         fields = ('url', 'username', 'email', 'groups')
#
#
# class GroupSerializer(serializers.HyperlinkedModelSerializer):
#     class Meta:
#         model = Group
#         fields = ('url', 'name')

# -*- coding: utf-8 -*-
from thingloc.quickstart.models import Usuario, Objetos, Categoria, Mensajes
from rest_framework import serializers
__author__ = 'flopez'


"""
El serializador de Usuarios
"""

class UsuarioSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Usuario
        fields = ('id','username','password','first_name','last_name','email')

"""
El serializador de Objetos
"""

class ObjetosSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Objetos
        fields = ('id','nombre','recompensa','perdido','foto','coordenadas','categoria')

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

class MensajesSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Mensajes
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

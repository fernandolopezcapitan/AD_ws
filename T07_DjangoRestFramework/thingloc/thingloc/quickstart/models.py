from django.contrib.auth.models import User
from django.db import models


# Create your models here.

# class Usuario(models.Model):
#     username = models.CharField(max_length=100)
#     password = models.CharField(max_length=10)
#     first_name = models.CharField(max_length=100)
#     last_name = models.CharField(max_length=100)
#     email = models.EmailField()

class Categoria(models.Model):
    nombre = models.CharField(max_length=100)

    def __str__(self):
        return self.nombre

class Objeto(models.Model):
    nombre = models.CharField(max_length=100)
    recompensa = models.FloatField()
    perdido = models.BooleanField()
    foto = models.ImageField(max_length=255,upload_to='images')
    coordenadas = models.CharField(max_length=100)
    categoria = models.ForeignKey(Categoria)
    usuario = models.ForeignKey(User)

    def __str__(self):
        return self.nombre

class Mensaje(models.Model):
    usuarioEmisor = models.ForeignKey(User, related_name='usuarioEmisor')
    usuarioReceptor = models.ForeignKey(User, related_name='usuarioReceptor')
    objeto = models.ForeignKey(Objeto)
    comentario = models.CharField(max_length=250)
    fecha = models.DateField()




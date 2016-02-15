from django.db import models


# Create your models here.

class Usuario(models.Model):
    username = models.CharField(max_length=100)
    password = models.CharField(max_length=10)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email = models.EmailField()

class Objetos(models.Model):
    nombre = models.CharField(max_length=100)
    recompensa = models.FloatField()
    perdido = models.BooleanField()
    foto = models.ImageField(max_length=255,upload_to='images')
    coordenadas = models.CharField(max_length=100)
    categoria = models.ForeignKey(Categoria)
    usuario = models.ForeignKey(Usuario)

class Categoria(models.Model):
    nombre = models.CharField(max_length=100)
    objecto = models.ForeignKey(Objetos)

class Mensajes(models):
    usuarioEmisor = models.ForeignKey(Usuario)
    usuarioReceptor = models.ForeignKey(Usuario)
    objecto = models.ForeignKey(Objetos)
    comentario = models.CharField(max_length=250)
    fecha = models.DateField()




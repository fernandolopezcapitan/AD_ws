from django.contrib import admin
from thingloc.quickstart.models import *
# Register your models here.



class UsuarioAdmin(admin.ModelAdmin):
    list_display = ('id','username','password','first_name','last_name','email',)
    list_filter = ('username','first_name','last_name','email',)
    ordering = ('username',)
    search_fields = ('username',)

class ObjetosAdmin(admin.ModelAdmin):
    list_display = ('id','nombre','recompensa','perdido','foto','coordenadas','categoria')
    list_filter = ('nombre','recompensa','categoria',)
    ordering = ('categoria',)
    search_fields = ('nombre',)

class CategoriaAdmin(admin.ModelAdmin):
    list_display = ('id','nombre',)
    list_filter = ('nombre',)
    ordering = ('nombre',)
    search_fields = ('nombre',)

class MensajesAdmin(admin.ModelAdmin):
    list_display = ('id','usuarioEmisor','usuarioReceptor','objeto','comentario','fecha')
    list_filter = ('usuarioEmisor','usuarioReceptor','objeto','comentario','fecha',)
    ordering = ('fecha',)
    search_fields = ('objeto',)

admin.site.register(Usuario, UsuarioAdmin)
admin.site.register(Objetos, ObjetosAdmin)
admin.site.register(Categoria, CategoriaAdmin)
admin.site.register(Mensajes, MensajesAdmin)
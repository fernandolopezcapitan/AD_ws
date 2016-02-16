# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('quickstart', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Mensaje',
            fields=[
                ('id', models.AutoField(serialize=False, verbose_name='ID', primary_key=True, auto_created=True)),
                ('comentario', models.CharField(max_length=250)),
                ('fecha', models.DateField()),
            ],
        ),
        migrations.RenameModel(
            old_name='Objetos',
            new_name='Objeto',
        ),
        migrations.RemoveField(
            model_name='mensajes',
            name='objecto',
        ),
        migrations.RemoveField(
            model_name='mensajes',
            name='usuarioEmisor',
        ),
        migrations.RemoveField(
            model_name='mensajes',
            name='usuarioReceptor',
        ),
        migrations.AlterField(
            model_name='objeto',
            name='usuario',
            field=models.ForeignKey(to=settings.AUTH_USER_MODEL),
        ),
        migrations.DeleteModel(
            name='Mensajes',
        ),
        migrations.DeleteModel(
            name='Usuario',
        ),
        migrations.AddField(
            model_name='mensaje',
            name='objecto',
            field=models.ForeignKey(to='quickstart.Objeto'),
        ),
        migrations.AddField(
            model_name='mensaje',
            name='usuarioEmisor',
            field=models.ForeignKey(to=settings.AUTH_USER_MODEL, related_name='usuarioEmisor'),
        ),
        migrations.AddField(
            model_name='mensaje',
            name='usuarioReceptor',
            field=models.ForeignKey(to=settings.AUTH_USER_MODEL, related_name='usuarioReceptor'),
        ),
    ]

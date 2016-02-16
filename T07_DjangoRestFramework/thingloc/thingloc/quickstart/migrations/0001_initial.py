# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Categoria',
            fields=[
                ('id', models.AutoField(auto_created=True, serialize=False, primary_key=True, verbose_name='ID')),
                ('nombre', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Mensajes',
            fields=[
                ('id', models.AutoField(auto_created=True, serialize=False, primary_key=True, verbose_name='ID')),
                ('comentario', models.CharField(max_length=250)),
                ('fecha', models.DateField()),
            ],
        ),
        migrations.CreateModel(
            name='Objetos',
            fields=[
                ('id', models.AutoField(auto_created=True, serialize=False, primary_key=True, verbose_name='ID')),
                ('nombre', models.CharField(max_length=100)),
                ('recompensa', models.FloatField()),
                ('perdido', models.BooleanField()),
                ('foto', models.ImageField(upload_to='images', max_length=255)),
                ('coordenadas', models.CharField(max_length=100)),
                ('categoria', models.ForeignKey(to='quickstart.Categoria')),
            ],
        ),
        migrations.CreateModel(
            name='Usuario',
            fields=[
                ('id', models.AutoField(auto_created=True, serialize=False, primary_key=True, verbose_name='ID')),
                ('username', models.CharField(max_length=100)),
                ('password', models.CharField(max_length=10)),
                ('first_name', models.CharField(max_length=100)),
                ('last_name', models.CharField(max_length=100)),
                ('email', models.EmailField(max_length=254)),
            ],
        ),
        migrations.AddField(
            model_name='objetos',
            name='usuario',
            field=models.ForeignKey(to='quickstart.Usuario'),
        ),
        migrations.AddField(
            model_name='mensajes',
            name='objecto',
            field=models.ForeignKey(to='quickstart.Objetos'),
        ),
        migrations.AddField(
            model_name='mensajes',
            name='usuarioEmisor',
            field=models.ForeignKey(related_name='usuarioEmisor', to='quickstart.Usuario'),
        ),
        migrations.AddField(
            model_name='mensajes',
            name='usuarioReceptor',
            field=models.ForeignKey(related_name='usuarioReceptor', to='quickstart.Usuario'),
        ),
    ]

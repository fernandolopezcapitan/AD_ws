# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('quickstart', '0002_auto_20160216_1017'),
    ]

    operations = [
        migrations.RenameField(
            model_name='mensaje',
            old_name='objecto',
            new_name='objeto',
        ),
    ]

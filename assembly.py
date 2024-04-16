# -*- coding: mbcs -*-
# Do not delete the following import lines
from abaqus import *
from abaqusConstants import *
import __main__

import section
import regionToolset
import displayGroupMdbToolset as dgm
import part
import material
import assembly
import step
import interaction
import load
import mesh
import optimization
import job
import sketch
import visualization
import xyPlot
import displayGroupOdbToolset as dgo
import connectorBehavior
import os


from part import *
from material import *
from section import *
from assembly import *
from step import *
from interaction import *
from load import *
from mesh import *
from optimization import *
from job import *
from sketch import *
from visualization import *
from connectorBehavior import *

#session.journalOptions.setValues(replayGeometry=COORDINATE, recoverGeometry=COORDINATE)


#hari
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['beam']
a1.Instance(name='c', part=p, dependent=ON)
a1.rotate(instanceList=('c', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('c', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('c', ), vector=(0.0, 100.0, 0.0))


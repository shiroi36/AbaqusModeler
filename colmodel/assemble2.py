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



#—À
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['weld1']
a.Instance(name='weld10', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('weld10', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld10', ), vector=(0.0, -9.0, -133.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld10', ), vector=(-250.0, 250.0, 0.0))

#—À
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['weld1']
a.Instance(name='weld12', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('weld12', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld12', ), vector=(0.0, -9.0, -133.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('weld12', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=180.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld12', ), vector=(-250.0, 500.0, 0.0))

#—À
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['weld2']
a.Instance(name='weld21', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('weld21', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld21', ), vector=(-9.0, 125.0, 133.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld21', ), vector=(-250.0, 375.0, 0.0))

#—À
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['weld2']
a.Instance(name='weld22', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('weld22', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld22', ), vector=(-9.0, 125.0, 133.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('weld22', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=180.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('weld22', ), vector=(-250.0, 375.0, 0.0))


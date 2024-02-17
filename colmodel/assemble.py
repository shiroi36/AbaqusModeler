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



#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['beam']
a.Instance(name='b0', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('b0', ), vector=(0.0, 0.0, -1500.0))
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=90.0)
#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28l']
a1.Instance(name='kpl-d0', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpl-d0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-d0', ), vector=(-368.0, -270.0, -20.0))

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28r']
a1.Instance(name='kpr-d0', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpr-d0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-d0', ), vector=(-368.0, -270.0, 20.0))

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28l']
a1.Instance(name='kpl-u0', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpl-u0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-u0', ), vector=(-368.0, -270.0, -20.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpl-u0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28r']
a1.Instance(name='kpr-u0', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpr-u0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-u0', ), vector=(-368.0, -270.0, 20.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpr-u0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#シアプレート
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['sp600_M24']
a1.Instance(name='sp0', part=p, dependent=ON)
a1.rotate(instanceList=('sp0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('sp0', ), vector=(0.0, -20.0, 6.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('b0', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-d0', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpl-d0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-d0', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpr-d0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-u0', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpl-u0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-u0', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpr-u0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('sp0', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('sp0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt00L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt00L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt00L', ), vector=(-343.0, 40.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt00L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt00L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt01L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt01L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt01L', ), vector=(-343.0, 100.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt01L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt01L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt02L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt02L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt02L', ), vector=(-343.0, 160.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt02L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt02L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt03L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt03L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt03L', ), vector=(-343.0, 220.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt03L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt03L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt04L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt04L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt04L', ), vector=(-343.0, 280.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt04L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt04L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt05L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt05L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt05L', ), vector=(-343.0, 340.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt05L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt05L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt06L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt06L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt06L', ), vector=(-343.0, 40.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt06L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt06L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt07L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt07L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt07L', ), vector=(-343.0, 100.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt07L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt07L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt08L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt08L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt08L', ), vector=(-343.0, 160.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt08L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt08L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt09L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt09L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt09L', ), vector=(-343.0, 220.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt09L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt09L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt10L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt10L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt10L', ), vector=(-343.0, 280.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt10L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt10L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt11L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt11L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt11L', ), vector=(-343.0, 340.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt11L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt11L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt12L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt12L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt12L', ), vector=(265.0, 40.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt12L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt12L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt13L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt13L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt13L', ), vector=(265.0, 100.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt13L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt13L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt14L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt14L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt14L', ), vector=(265.0, 160.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt14L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt14L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt15L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt15L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt15L', ), vector=(265.0, 220.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt15L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt15L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt16L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt16L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt16L', ), vector=(265.0, 280.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt16L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt16L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt17L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt17L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt17L', ), vector=(265.0, 340.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt17L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt17L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt18L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt18L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt18L', ), vector=(265.0, 40.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt18L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt18L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt19L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt19L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt19L', ), vector=(265.0, 100.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt19L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt19L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt20L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt20L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt20L', ), vector=(265.0, 160.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt20L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt20L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt21L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt21L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt21L', ), vector=(265.0, 220.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt21L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt21L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt22L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt22L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt22L', ), vector=(265.0, 280.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt22L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt22L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt23L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt23L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt23L', ), vector=(265.0, 340.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt23L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt23L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt00L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt00L', ), vector=(-338.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt00L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt00L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt01L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt01L', ), vector=(-338.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt01L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt01L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt02L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt02L', ), vector=(-338.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt02L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt02L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt03L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt03L', ), vector=(-398.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt03L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt03L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt04L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt04L', ), vector=(-398.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt04L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt04L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt05L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt05L', ), vector=(-398.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt05L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt05L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt06L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt06L', ), vector=(398.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt06L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt06L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt07L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt07L', ), vector=(398.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt07L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt07L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt08L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt08L', ), vector=(398.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt08L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt08L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt09L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt09L', ), vector=(338.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt09L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt09L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt10L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt10L', ), vector=(338.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt10L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt10L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt11L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt11L', ), vector=(338.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt11L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt11L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt00L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt00L', ), vector=(187.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt00L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt00L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt01L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt01L', ), vector=(112.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt01L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt01L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt02L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt02L', ), vector=(37.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt02L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt02L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt03L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt03L', ), vector=(-37.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt03L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt03L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt04L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt04L', ), vector=(-112.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt04L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt04L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt05L', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt05L', ), vector=(-187.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt05L', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt05L', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['beam']
a.Instance(name='b1', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('b1', ), vector=(0.0, 0.0, -1500.0))
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=90.0)
#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28l']
a1.Instance(name='kpl-d1', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpl-d1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-d1', ), vector=(-368.0, -270.0, -20.0))

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28r']
a1.Instance(name='kpr-d1', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpr-d1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-d1', ), vector=(-368.0, -270.0, 20.0))

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28l']
a1.Instance(name='kpl-u1', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpl-u1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-u1', ), vector=(-368.0, -270.0, -20.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpl-u1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['r28r']
a1.Instance(name='kpr-u1', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpr-u1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-u1', ), vector=(-368.0, -270.0, 20.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpr-u1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#シアプレート
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['sp600_M24']
a1.Instance(name='sp1', part=p, dependent=ON)
a1.rotate(instanceList=('sp1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('sp1', ), vector=(0.0, -20.0, 6.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('b1', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-d1', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpl-d1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-d1', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpr-d1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-u1', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpl-u1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-u1', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpr-u1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('sp1', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('sp1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt00R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt00R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt00R', ), vector=(-343.0, 40.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt00R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt00R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt01R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt01R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt01R', ), vector=(-343.0, 100.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt01R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt01R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt02R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt02R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt02R', ), vector=(-343.0, 160.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt02R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt02R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt03R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt03R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt03R', ), vector=(-343.0, 220.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt03R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt03R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt04R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt04R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt04R', ), vector=(-343.0, 280.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt04R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt04R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt05R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt05R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt05R', ), vector=(-343.0, 340.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt05R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt05R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt06R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt06R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt06R', ), vector=(-343.0, 40.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt06R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt06R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt07R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt07R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt07R', ), vector=(-343.0, 100.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt07R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt07R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt08R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt08R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt08R', ), vector=(-343.0, 160.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt08R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt08R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt09R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt09R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt09R', ), vector=(-343.0, 220.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt09R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt09R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt10R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt10R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt10R', ), vector=(-343.0, 280.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt10R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt10R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt11R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt11R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt11R', ), vector=(-343.0, 340.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt11R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt11R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt12R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt12R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt12R', ), vector=(265.0, 40.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt12R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt12R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt13R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt13R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt13R', ), vector=(265.0, 100.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt13R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt13R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt14R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt14R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt14R', ), vector=(265.0, 160.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt14R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt14R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt15R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt15R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt15R', ), vector=(265.0, 220.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt15R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt15R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt16R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt16R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt16R', ), vector=(265.0, 280.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt16R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt16R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt17R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt17R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt17R', ), vector=(265.0, 340.0, -88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt17R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt17R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt18R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt18R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt18R', ), vector=(265.0, 40.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt18R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt18R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt19R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt19R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt19R', ), vector=(265.0, 100.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt19R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt19R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt20R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt20R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt20R', ), vector=(265.0, 160.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt20R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt20R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt21R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt21R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt21R', ), vector=(265.0, 220.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt21R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt21R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt22R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt22R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt22R', ), vector=(265.0, 280.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt22R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt22R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt23R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt23R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt23R', ), vector=(265.0, 340.0, 88.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt23R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt23R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt00R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt00R', ), vector=(-338.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt00R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt00R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt01R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt01R', ), vector=(-338.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt01R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt01R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt02R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt02R', ), vector=(-338.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt02R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt02R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt03R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt03R', ), vector=(-398.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt03R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt03R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt04R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt04R', ), vector=(-398.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt04R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt04R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt05R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt05R', ), vector=(-398.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt05R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt05R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt06R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt06R', ), vector=(398.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt06R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt06R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt07R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt07R', ), vector=(398.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt07R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt07R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt08R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt08R', ), vector=(398.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt08R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt08R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt09R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt09R', ), vector=(338.0, -110.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt09R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt09R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt10R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt10R', ), vector=(338.0, -170.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt10R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt10R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt11R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt11R', ), vector=(338.0, -230.0, -63.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt11R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwbolt11R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt00R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt00R', ), vector=(187.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt00R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt00R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt01R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt01R', ), vector=(112.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt01R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt01R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt02R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt02R', ), vector=(37.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt02R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt02R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt03R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt03R', ), vector=(-37.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt03R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt03R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt04R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt04R', ), vector=(-112.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt04R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt04R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt05R', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt05R', ), vector=(-187.5, 40.0, -21.0))

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt05R', ), vector=(0.0, 270.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bwbolt05R', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=-90.0)

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cfr']
a.Instance(name='cfr0', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cfr0', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cfr0', ), vector=(250.0, 375.0, 0.0))

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cfr']
a.Instance(name='cfr1', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cfr1', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cfr1', ), vector=(250.0, -375.0, 0.0))

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cfr']
a.Instance(name='cfr2', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cfr2', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cfr2', ), vector=(250.0, 375.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cfr2', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cfr']
a.Instance(name='cfr3', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cfr3', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cfr3', ), vector=(250.0, -375.0, 0.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cfr3', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cwr']
a.Instance(name='cwr0', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwr0', ), vector=(0.0, 368.0, 8.0))

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cwr']
a.Instance(name='cwr1', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwr1', ), vector=(0.0, -368.0, 8.0))

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cwr']
a.Instance(name='cwr2', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwr2', ), vector=(0.0, 368.0, 8.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwr2', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['cwr']
a.Instance(name='cwr3', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwr3', ), vector=(0.0, -368.0, 8.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('cwr3', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['col']
a1.Instance(name='col', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('col', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('col', ), vector=(0.0, 0.0, -1000.0))

a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('col', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=90.0)

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['panel']
a.Instance(name='panel1', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('panel1', ), vector=(0.0, 0.0, 8.0))

#梁
a = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['panel']
a.Instance(name='panel2', part=p, dependent=ON)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('panel2', ), vector=(0.0, 0.0, -24.0))


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
a.Instance(name='b', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('b', ), vector=(0.0, 0.0, -1500.0))
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('b', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
1.0, 0.0, 0.0), angle=90.0)
#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['n25l']
a1.Instance(name='kpl-d', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpl-d', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-d', ), vector=(-365.0, -270.0, -12.0))

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['n25r']
a1.Instance(name='kpr-d', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpr-d', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-d', ), vector=(-365.0, -270.0, 12.0))

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['n25l']
a1.Instance(name='kpl-u', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpl-u', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpl-u', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpl-u', ), vector=(365.0, -270.0, 12.0))

#金物
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['n25r']
a1.Instance(name='kpr-u', part=p, dependent=ON)
a1 = mdb.models['Model-1'].rootAssembly
a1.rotate(instanceList=('kpr-u', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)


a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('kpr-u', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=180.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('kpr-u', ), vector=(365.0, -270.0, -12.0))

#シアプレート
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['sp600_M22']
a1.Instance(name='sp', part=p, dependent=ON)
a1.rotate(instanceList=('sp', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('sp', ), vector=(0.0, -20.0, 6.0))

#エンドプレート
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['endplate']
a1.Instance(name='ep', part=p, dependent=ON)
a1.rotate(instanceList=('ep', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 0.0, 1.0), angle=90.0)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('ep', ), vector=(0.0, -310.0, 0.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt00', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt00', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt00', ), vector=(266.0, 40.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt01', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt01', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt01', ), vector=(266.0, 100.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt02', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt02', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt02', ), vector=(266.0, 160.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt03', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt03', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt03', ), vector=(266.0, 220.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt04', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt04', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt04', ), vector=(266.0, 280.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt05', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt05', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt05', ), vector=(266.0, 340.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt06', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt06', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt06', ), vector=(266.0, 40.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt07', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt07', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt07', ), vector=(266.0, 100.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt08', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt08', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt08', ), vector=(266.0, 160.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt09', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt09', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt09', ), vector=(266.0, 220.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt10', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt10', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt10', ), vector=(266.0, 280.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt11', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt11', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt11', ), vector=(266.0, 340.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt12', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt12', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt12', ), vector=(-339.0, 40.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt13', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt13', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt13', ), vector=(-339.0, 100.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt14', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt14', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt14', ), vector=(-339.0, 160.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt15', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt15', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt15', ), vector=(-339.0, 220.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt16', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt16', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt16', ), vector=(-339.0, 280.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt17', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt17', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt17', ), vector=(-339.0, 340.0, -80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt18', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt18', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt18', ), vector=(-339.0, 40.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt19', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt19', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt19', ), vector=(-339.0, 100.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt20', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt20', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt20', ), vector=(-339.0, 160.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt21', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt21', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt21', ), vector=(-339.0, 220.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt22', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt22', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt22', ), vector=(-339.0, 280.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt1']
a1.Instance(name='bfbolt23', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.rotate(instanceList=('bfbolt23', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(
0.0, 1.0, 0.0), angle=90.0)

a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bfbolt23', ), vector=(-339.0, 340.0, 80.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt00', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt00', ), vector=(395.0, -110.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt01', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt01', ), vector=(395.0, -170.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt02', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt02', ), vector=(395.0, -230.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt03', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt03', ), vector=(335.0, -110.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt04', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt04', ), vector=(335.0, -170.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt05', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt05', ), vector=(335.0, -230.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt06', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt06', ), vector=(-335.0, -110.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt07', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt07', ), vector=(-335.0, -170.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt08', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt08', ), vector=(-335.0, -230.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt09', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt09', ), vector=(-395.0, -110.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt10', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt10', ), vector=(-395.0, -170.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt2']
a1.Instance(name='cwbolt11', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('cwbolt11', ), vector=(-395.0, -230.0, -51.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt00', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt00', ), vector=(187.5, 40.0, -20.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt01', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt01', ), vector=(112.5, 40.0, -20.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt02', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt02', ), vector=(37.5, 40.0, -20.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt03', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt03', ), vector=(-37.5, 40.0, -20.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt04', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt04', ), vector=(-112.5, 40.0, -20.0))

#ボルト
a1 = mdb.models['Model-1'].rootAssembly
p = mdb.models['Model-1'].parts['bolt3']
a1.Instance(name='bwbolt05', part=p, dependent=ON)
a = mdb.models['Model-1'].rootAssembly
a.translate(instanceList=('bwbolt05', ), vector=(-187.5, 40.0, -20.0))


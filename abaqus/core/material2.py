# -*- coding: mbcs -*-
# Do not delete the following import lines
from abaqus import *
from abaqusConstants import *

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

#F14TおよびF10T→質量を16倍にした(マススケーリング法)
#同様に、接合金物(490k,400k)も質量を16倍にしている。
mdb.models['Model-1'].Material(name='F14T')
mdb.models['Model-1'].materials['F14T'].Density(table=((124.8e-05, ), ))
mdb.models['Model-1'].materials['F14T'].Elastic(table=((205000.0, 0.3), 
))
mdb.models['Model-1'].materials['F14T'].Plastic(table=((1260.0, 0.0), 
(2400.0, 1.0)))

mdb.models['Model-1'].Material(name='F10T')
mdb.models['Model-1'].materials['F10T'].Density(table=((124.8e-05, ), ))
mdb.models['Model-1'].materials['F10T'].Elastic(table=((205000.0, 0.3), 
))
mdb.models['Model-1'].materials['F10T'].Plastic(table=((900.0, 0.0), 
(1700.0, 1.0)))

mdb.models['Model-1'].HomogeneousSolidSection(name='F14T', material='F14T', thickness=None)
mdb.models['Model-1'].HomogeneousSolidSection(name='F10T', material='F10T', thickness=None)



mdb.models['Model-1'].Material(name='sm490')
mdb.models['Model-1'].materials['sm490'].Density(table=((7.8e-05, ), ))
mdb.models['Model-1'].materials['sm490'].Elastic(table=((205000.0, 0.3), ))

mdb.models['Model-1'].HomogeneousSolidSection(name='sm490', material='sm490', thickness=None)

mdb.models['Model-1'].Material(name='ss400')
mdb.models['Model-1'].materials['ss400'].Elastic(table=((205000.0, 0.3), ))
mdb.models['Model-1'].materials['ss400'].Density(table=((7.8e-05, ), ))

mdb.models['Model-1'].HomogeneousSolidSection(name='ss400', material='ss400', thickness=None)


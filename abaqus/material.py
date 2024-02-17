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
mdb.models['Model-1'].materials['F14T'].Density(table=((7.8e-05, ), ))
mdb.models['Model-1'].materials['F14T'].Elastic(table=((205000.0, 0.3), 
))
mdb.models['Model-1'].materials['F14T'].Plastic(table=((1260.0, 0.0), 
(2400.0, 1.0)))

mdb.models['Model-1'].Material(name='F10T')
mdb.models['Model-1'].materials['F10T'].Density(table=((7.8e-05, ), ))
mdb.models['Model-1'].materials['F10T'].Elastic(table=((205000.0, 0.3), 
))
mdb.models['Model-1'].materials['F10T'].Plastic(table=((900.0, 0.0), 
(1700.0, 1.0)))

mdb.models['Model-1'].HomogeneousSolidSection(name='F14T', material='F14T', thickness=None)
mdb.models['Model-1'].HomogeneousSolidSection(name='F10T', material='F10T', thickness=None)


mdb.models['Model-1'].Material(name='steel')
mdb.models['Model-1'].materials['steel'].Density(table=((7.8e-05, ), ))
mdb.models['Model-1'].materials['steel'].Elastic(table=((205000.0, 0.3), ))
mdb.models['Model-1'].HomogeneousSolidSection(name='steel', material='steel', thickness=None)


mdb.models['Model-1'].Material(name='sm490flat')
mdb.models['Model-1'].materials['sm490flat'].Density(table=((7.8e-05, ), ))
mdb.models['Model-1'].materials['sm490flat'].Elastic(table=((205000.0, 0.3), ))
mdb.models['Model-1'].materials['sm490flat'].Plastic(table=(
(325.0, 0.0), 
(326.0, 1.0)))
mdb.models['Model-1'].HomogeneousSolidSection(name='sm490flat', material='sm490flat', thickness=None)



mdb.models['Model-1'].Material(name='sm490')
mdb.models['Model-1'].materials['sm490'].Density(table=((7.8e-05, ), ))
mdb.models['Model-1'].materials['sm490'].Elastic(table=((205000.0, 0.3), ))
mdb.models['Model-1'].materials['sm490'].Plastic(table=(
(354.9,0.000),
(354.8,0.017),
(402.7,0.027),
(436.3,0.037),
(462.1,0.047),
(483.2,0.057),
(501.0,0.067),
(516.3,0.077),
(529.9,0.087),
(542.0,0.097),
(552.9,0.107),
(562.9,0.117),
(572.0,0.127),
(580.5,0.137),
(588.4,0.147),
(595.8,0.157),
(602.7,0.167),
(609.2,0.177),
(615.4,0.187),
(621.3,0.197),
(623,1.0)
))

mdb.models['Model-1'].HomogeneousSolidSection(name='sm490', material='sm490', thickness=None)



mdb.models['Model-1'].Material(name='ss400flat')
mdb.models['Model-1'].materials['ss400flat'].Density(table=((7.8e-05, ), ))
mdb.models['Model-1'].materials['ss400flat'].Elastic(table=((205000.0, 0.3), ))
mdb.models['Model-1'].materials['ss400flat'].Plastic(table=(
(235.0, 0.0), 
(236.0, 1.0)))
mdb.models['Model-1'].HomogeneousSolidSection(name='ss400flat', material='ss400flat', thickness=None)



mdb.models['Model-1'].Material(name='ss400')
mdb.models['Model-1'].materials['ss400'].Elastic(table=((205000.0, 0.3), ))
mdb.models['Model-1'].materials['ss400'].Plastic(table=(
(235.0, 0.0), 
(235.0, 0.02), 
(332.49, 0.05), 
(382.5, 0.08), 
(416.39, 0.11), 
(442.05, 0.14), 
(462.71, 0.17), 
(480.0, 0.2), 
(481.0, 1.0)
))
mdb.models['Model-1'].materials['ss400'].Density(table=((7.8e-05, ), ))

mdb.models['Model-1'].HomogeneousSolidSection(name='ss400', material='ss400', thickness=None)


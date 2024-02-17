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



time1=0.6
time2=0.6
time3=6.0
num=100
num2=10





mdb.models['Model-1'].ExplicitDynamicsStep(name='Step-1', previous='Initial', 
timePeriod=time1)
#mdb.models['Model-1'].ExplicitDynamicsStep(name='Step-2', previous='Step-1', 
#timePeriod=time2)
#mdb.models['Model-1'].ExplicitDynamicsStep(name='Step-3', previous='Step-2', 
#timePeriod=time3)
mdb.models['Model-1'].ExplicitDynamicsStep(name='Step-3', previous='Step-1', 
timePeriod=time3)

mdb.models['Model-1'].SmoothStepAmplitude(name='p1', timeSpan=STEP, data=((0.0, 
0.0), (time1/2, 1.0), (time1, 1.0)))
mdb.models['Model-1'].SmoothStepAmplitude(name='p2', timeSpan=STEP, data=((0.0, 
0.0), (time1/2, 0.0), (time1, 1.0)))
#mdb.models['Model-1'].SmoothStepAmplitude(name='axial', timeSpan=STEP, data=((
#0.0, 0.0), (time2, 1.0)))
mdb.models['Model-1'].SmoothStepAmplitude(name='main', timeSpan=STEP, data=((
0.0, 0.0), (time3, 1.0)))


mdb.models['Model-1'].FieldOutputRequest(name='F-Output-1', 
createStepName='Step-1', variables=('S', 'E', 'PE', 'PEEQ', 'CSTRESS', 
'CFORCE'), timeInterval=time1/num2, timeMarks=OFF)
#mdb.models['Model-1'].FieldOutputRequest(name='F-Output-2', 
#createStepName='Step-2', variables=('S', 'E', 'PE', 'PEEQ', 'CSTRESS', 
#'CFORCE'), timeInterval=time2/num2, timeMarks=OFF)
mdb.models['Model-1'].FieldOutputRequest(name='F-Output-3', 
createStepName='Step-3', variables=('S', 'E', 'PE', 'PEEQ', 'U', 'UT', 
'UR', 'RF', 'RT', 'RM', 'CSTRESS', 'CFORCE'), timeInterval=time3/num)

mdb.models['Model-1'].fieldOutputRequests['F-Output-1'].deactivate('Step-3')
#mdb.models['Model-1'].fieldOutputRequests['F-Output-1'].deactivate('Step-2')
#mdb.models['Model-1'].fieldOutputRequests['F-Output-2'].deactivate('Step-3')

mdb.models['Model-1'].steps['Step-1'].setValues(massScaling=((SEMI_AUTOMATIC, 
MODEL, THROUGHOUT_STEP, 0.0, 2e-05, BELOW_MIN, 1, 0, 0.0, 0.0, 0, 
None), ))

mdb.models['Model-1'].historyOutputRequests['H-Output-1'].setValues(variables=(
'ALLAE', 'ALLCD', 'ALLDC', 'ALLDMD', 'ALLFD', 'ALLIE', 'ALLKE', 
'ALLPD', 'ALLSE', 'ALLVD', 'ALLWK', 'ALLCW', 'ALLMW', 'ALLPW', 
'ETOTAL'))


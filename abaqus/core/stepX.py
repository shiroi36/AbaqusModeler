
mdb.models['Model-1'].ExplicitDynamicsStep(name='Step-1', previous='Initial', 
timePeriod=time1)
mdb.models['Model-1'].ExplicitDynamicsStep(name='Step-2', previous='Step-1', 
timePeriod=time2)
mdb.models['Model-1'].ExplicitDynamicsStep(name='Step-3', previous='Step-2', 
timePeriod=time3)

mdb.models['Model-1'].SmoothStepAmplitude(name='p1', timeSpan=STEP, data=((0.0, 
0.0), (time1/2, 1.0), (time1, 1.0)))
mdb.models['Model-1'].SmoothStepAmplitude(name='p2', timeSpan=STEP, data=((0.0, 
0.0), (time1/2, 0.0), (time1, 1.0)))
mdb.models['Model-1'].SmoothStepAmplitude(name='axial', timeSpan=STEP, data=((
0.0, 0.0), (time2, 1.0)))
mdb.models['Model-1'].SmoothStepAmplitude(name='main', timeSpan=STEP, data=((
0.0, 0.0), (time3, 1.0)))


mdb.models['Model-1'].FieldOutputRequest(name='F-Output-1', 
createStepName='Step-1', variables=('S', 'E', 'PE', 'PEEQ', 'CSTRESS', 
'CFORCE'), timeInterval=time1/num2, timeMarks=OFF)
mdb.models['Model-1'].FieldOutputRequest(name='F-Output-2', 
createStepName='Step-2', variables=('S', 'E', 'PE', 'PEEQ', 'CSTRESS', 
'CFORCE'), timeInterval=time2/num2, timeMarks=OFF)
mdb.models['Model-1'].FieldOutputRequest(name='F-Output-3', 
createStepName='Step-3', variables=('S', 'E', 'PE', 'PEEQ', 'U', 'UT', 
'UR', 'RF', 'RT', 'RM', 'CSTRESS', 'CFORCE'), timeInterval=time3/num)

mdb.models['Model-1'].fieldOutputRequests['F-Output-1'].deactivate('Step-3')
mdb.models['Model-1'].fieldOutputRequests['F-Output-1'].deactivate('Step-2')
mdb.models['Model-1'].fieldOutputRequests['F-Output-2'].deactivate('Step-3')


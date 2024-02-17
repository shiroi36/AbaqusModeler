
#------------------------------------------------------------------------------------
#ã´äEèåèÇÃê›íË
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[end1], r1[end2], )
region = regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].DisplacementBC(name='BC-1', createStepName='Initial', 
region=region, u1=SET, u2=SET, u3=SET, ur1=SET, ur2=SET, ur3=SET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)

a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[p1], )
region = regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].DisplacementBC(name='BC-2', createStepName='Initial', 
region=region, u1=UNSET, u2=UNSET, u3=SET, ur1=SET, ur2=SET, ur3=UNSET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)
mdb.models['Model-1'].boundaryConditions['BC-2'].setValuesInStep(
stepName='Step-2', ur3=dx, amplitude='main')



#É{ÉãÉgÇÃê›íË
a = mdb.models['Model-1'].rootAssembly
region = a.surfaces['p1']
mdb.models['Model-1'].Pressure(name='Load-1', createStepName='Step-1', 
region=region, distributionType=UNIFORM, field='', magnitude=inip, 
amplitude='p1')
a = mdb.models['Model-1'].rootAssembly
region = a.surfaces['p2']
mdb.models['Model-1'].Pressure(name='Load-2', createStepName='Step-1', 
region=region, distributionType=UNIFORM, field='', magnitude=inip, 
amplitude='p2')





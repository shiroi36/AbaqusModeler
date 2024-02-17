
#------------------------------------------------------------------------------------
#ã´äEèåèÇÃê›íË

a = mdb.models['Model-1'].rootAssembly
region = a.sets['RP4']
mdb.models['Model-1'].DisplacementBC(name='B0', createStepName='Initial', 
region=region, u1=UNSET, u2=UNSET, u3=SET, ur1=SET, ur2=SET, ur3=UNSET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)

a = mdb.models['Model-1'].rootAssembly
region = a.sets['RP3']
mdb.models['Model-1'].DisplacementBC(name='B1', createStepName='Initial', 
region=region, u1=UNSET, u2=UNSET, u3=SET, ur1=SET, ur2=SET, ur3=UNSET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)

a = mdb.models['Model-1'].rootAssembly
region = a.sets['RP2']
mdb.models['Model-1'].DisplacementBC(name='C1', createStepName='Initial', 
region=region, u1=SET, u2=UNSET, u3=SET, ur1=SET, ur2=SET, ur3=UNSET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)

a = mdb.models['Model-1'].rootAssembly
region = a.sets['RP1']
mdb.models['Model-1'].DisplacementBC(name='C0', createStepName='Initial', 
region=region, u1=SET, u2=UNSET, u3=SET, ur1=SET, ur2=SET, ur3=UNSET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)

mdb.models['Model-1'].boundaryConditions['B0'].setValuesInStep(
stepName='Step-3', u2=dx, amplitude='main')

mdb.models['Model-1'].boundaryConditions['B1'].setValuesInStep(
stepName='Step-3', u2=-dx, amplitude='main')


#a = mdb.models['Model-1'].rootAssembly
#region = a.sets['RP1']
#mdb.models['Model-1'].ConcentratedForce(name='Load-1a', createStepName='Step-2', 
#region=region, cf2=-pc, amplitude='axial', distributionType=UNIFORM, 
#field='', localCsys=None)

#a = mdb.models['Model-1'].rootAssembly
#region = a.sets['RP2']
#mdb.models['Model-1'].ConcentratedForce(name='Load-1b', createStepName='Step-2', 
#region=region, cf2=pc, amplitude='axial', distributionType=UNIFORM, 
#field='', localCsys=None)





#É{ÉãÉgÇÃê›íË
a = mdb.models['Model-1'].rootAssembly
region = a.surfaces['p0']
mdb.models['Model-1'].Pressure(name='Load-1', createStepName='Step-1', 
region=region, distributionType=UNIFORM, field='', magnitude=inip, 
amplitude='p1')
a = mdb.models['Model-1'].rootAssembly
region = a.surfaces['p1']
mdb.models['Model-1'].Pressure(name='Load-2', createStepName='Step-1', 
region=region, distributionType=UNIFORM, field='', magnitude=inip, 
amplitude='p2')





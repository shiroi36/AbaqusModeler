




#--------------------------------------------

del mdb.models['Model-1'].boundaryConditions['BC-2']
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[182], )
region = regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].DisplacementBC(name='BC-2', createStepName='Initial', 
region=region, u1=UNSET, u2=UNSET, u3=SET, ur1=SET, ur2=SET, ur3=UNSET, 
amplitude=UNSET, distributionType=UNIFORM, fieldName='', 
localCsys=None)
mdb.models['Model-1'].boundaryConditions['BC-2'].move('Initial', 'Step-1')
mdb.models['Model-1'].boundaryConditions['BC-2'].setValuesInStep(
stepName='Step-2', u1=v0, amplitude='main')


a = mdb.models['Model-1'].rootAssembly
f1 = a.instances['b'].faces
faces1 = f1.getSequenceFromMask(mask=(
'[#ffffffff:10 #75ffffff #ee573398 #56aa954a #fafd7ead #7ffdfffb #fffffdfd', 
' #87f09f ]', ), )
c2 = a.instances['kpl-d'].cells
cells2 = c2.getSequenceFromMask(mask=('[#40000 ]', ), )
c3 = a.instances['kpr-d'].cells
cells3 = c3.getSequenceFromMask(mask=('[#40000 ]', ), )
c4 = a.instances['kpl-u'].cells
cells4 = c4.getSequenceFromMask(mask=('[#40000 ]', ), )
c5 = a.instances['kpr-u'].cells
cells5 = c5.getSequenceFromMask(mask=('[#40000 ]', ), )
r6 = a.referencePoints
refPoints6=(r6[182], r6[183], r6[184], )
a.Set(faces=faces1, cells=cells2+cells3+cells4+cells5, 
referencePoints=refPoints6, name='outputnode_u')


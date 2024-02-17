

a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[p0],r1[p1], r1[end1], r1[end2], )
a.Set(referencePoints=refPoints1, name='outputnode_rf')

a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[p0],r1[p1], r1[end1], r1[end2], )
a.Set(referencePoints=refPoints1, name='outputnode_u')

a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[p0],r1[p1], r1[end1], r1[end2], )
a.Set(referencePoints=refPoints1, name='outputelement')




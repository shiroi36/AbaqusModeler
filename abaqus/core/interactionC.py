

#------------------------------------------------------------------------------------
a = mdb.models['Model-1'].rootAssembly
d1 = a.instances['b'].datums
a.ReferencePoint(point=d1[5])
a = mdb.models['Model-1'].rootAssembly
e1 = a.instances['ep'].edges
a.ReferencePoint(point=a.instances['ep'].InterestingPoint(edge=e1[35], 
rule=MIDDLE))
a = mdb.models['Model-1'].rootAssembly
e11 = a.instances['ep'].edges
a.ReferencePoint(point=a.instances['ep'].InterestingPoint(edge=e11[50], 
rule=MIDDLE))


mdb.models['Model-1'].ContactProperty('IntProp-1')
mdb.models['Model-1'].interactionProperties['IntProp-1'].TangentialBehavior(
formulation=PENALTY, directionality=ISOTROPIC, slipRateDependency=OFF, 
pressureDependency=OFF, temperatureDependency=OFF, dependencies=0, 
table=((0.45, ), ), shearStressLimit=None, maximumElasticSlip=FRACTION, 
fraction=0.005, elasticSlipStiffness=None)

#載荷点のの剛体設定
a = mdb.models['Model-1'].rootAssembly
e1 = a.instances['b'].edges
edges1 = e1.getByBoundingBox(-1000,lb-10,-1000,1000,lb+10,1000,)
region3=regionToolset.Region(edges=edges1)
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[p1], )
region1=regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].RigidBody(name='LP', refPointRegion=region1, 
pinRegion=region3)

#端部の剛体設定
a = mdb.models['Model-1'].rootAssembly
e1 = a.instances['ep'].edges
edges1 = e1.getSequenceFromMask(mask=('[#62000000 #8 ]', ), )
region3=regionToolset.Region(edges=edges1)
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[end1], )
region1=regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].RigidBody(name='E1', refPointRegion=region1, 
pinRegion=region3)

a = mdb.models['Model-1'].rootAssembly
e1 = a.instances['ep'].edges
edges1 = e1.getSequenceFromMask(mask=('[#0 #e40000 ]', ), )
region3=regionToolset.Region(edges=edges1)
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[end2], )
region1=regionToolset.Region(referencePoints=refPoints1)
mdb.models['Model-1'].RigidBody(name='E2', refPointRegion=region1, 
pinRegion=region3)


#------------------------------------------------------------------------------------
#ボルトサーフェスの作成
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt01'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt02'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt03'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt04'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt05'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b0')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt07'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt08'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt09'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt10'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt11'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b1')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt13'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt14'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt15'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt16'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt17'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b2')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt18'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt19'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt20'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt21'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt22'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt23'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b3')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt01'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt02'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt03'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['cwbolt04'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['cwbolt05'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b4')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt07'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt08'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt09'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['cwbolt10'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['cwbolt11'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b5')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bwbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bwbolt01'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bwbolt02'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bwbolt03'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bwbolt04'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bwbolt05'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
#s7 = a.instances['bwbolt06'].faces
#side1Faces7 = s7.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6\
#+side1Faces7
, name='b6')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getByBoundingBox(h/2-tf-tf/10,5,5, h/2-tf+tf/10,375,1000)
s2 = a.instances['b'].faces
side1Faces2 = s2.getByBoundingBox(h/2-tf-tf/10,5,-1000, h/2-tf+tf/10,375,-5)

s3 = a.instances['b'].faces
side1Faces3 = s3.getByBoundingBox(-h/2+tf-tf/10,5,5, -h/2+tf+tf/10,375,1000)
s4 = a.instances['b'].faces
side1Faces4 = s4.getByBoundingBox(-h/2+tf-tf/10,5,-1000, -h/2+tf+tf/10,375,-5)

s5 = a.instances['kpl-u'].faces
side1Faces5 = s5.getByBoundingBox((h/2+tk1)-5,5,5, (h/2+tk1)+5,375,1000)
s6 = a.instances['kpr-u'].faces
side1Faces6 = s6.getByBoundingBox((h/2+tk1)-5,5,-1005, (h/2+tk1)+5,375,-5)

s7 = a.instances['kpr-d'].faces
side1Faces7 = s7.getByBoundingBox(-(h/2+tk1)-5,5,5, -(h/2+tk1)+5,375,1000)
s8 = a.instances['kpl-d'].faces
side1Faces8 = s8.getByBoundingBox(-(h/2+tk1)-5,5,-1005, -(h/2+tk1)+5,375,-5)

a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+side1Faces5+side1Faces6+side1Faces7+side1Faces8,
 name='p2')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['sp'].faces
side1Faces1 = s1.getByBoundingBox(-hs/2+5,5,tw/2+ts-2, hs/2-5,375,tw/2+ts+2)
s2 = a.instances['b'].faces
side1Faces2 = s2.getByBoundingBox(-hs/2+5, 5,-tw/2-1, hs/2-5, 375, -tw/2+2)

s3 = a.instances['kpl-u'].faces
side1Faces3 = s3.getByBoundingBox(dk/2-60, -269,bk/2, dk/2+60, -71, 1000)
s4 = a.instances['kpr-u'].faces
side1Faces4 = s4.getByBoundingBox(dk/2-60, -269,-1000, dk/2+60, -71, -bk/2)
s5 = a.instances['kpr-d'].faces
side1Faces5 = s5.getByBoundingBox(-dk/2-60, -269,bk/2, -dk/2+60, -71, 1000)
s6 = a.instances['kpl-d'].faces
side1Faces6 = s6.getByBoundingBox(-dk/2-60, -269,-1000, -dk/2+60, -71, -bk/2)

a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+side1Faces5+side1Faces6, name='p1')

#ボルト接合部の設定
mdb.models['Model-1'].ContactExp(name='Int-1', createStepName='Initial')
r11=mdb.models['Model-1'].rootAssembly.surfaces['b0']
r12=mdb.models['Model-1'].rootAssembly.instances['b'].surfaces['b4']
r21=mdb.models['Model-1'].rootAssembly.surfaces['b0']
r22=mdb.models['Model-1'].rootAssembly.instances['kpr-u'].surfaces['b2']
r31=mdb.models['Model-1'].rootAssembly.surfaces['b1']
r32=mdb.models['Model-1'].rootAssembly.instances['b'].surfaces['b3']
r41=mdb.models['Model-1'].rootAssembly.surfaces['b1']
r42=mdb.models['Model-1'].rootAssembly.instances['kpl-u'].surfaces['b2']
r51=mdb.models['Model-1'].rootAssembly.surfaces['b3']
r52=mdb.models['Model-1'].rootAssembly.instances['b'].surfaces['b1']
r61=mdb.models['Model-1'].rootAssembly.surfaces['b3']
r62=mdb.models['Model-1'].rootAssembly.instances['kpr-d'].surfaces['b2']
r71=mdb.models['Model-1'].rootAssembly.surfaces['b2']
r72=mdb.models['Model-1'].rootAssembly.instances['b'].surfaces['b2']
r81=mdb.models['Model-1'].rootAssembly.surfaces['b2']
r82=mdb.models['Model-1'].rootAssembly.instances['kpl-d'].surfaces['b2']
r91=mdb.models['Model-1'].rootAssembly.surfaces['b4']
r92=mdb.models['Model-1'].rootAssembly.instances['ep'].surfaces['b2']
r101=mdb.models['Model-1'].rootAssembly.surfaces['b4']
r102=mdb.models['Model-1'].rootAssembly.instances['kpl-u'].surfaces['b1']
r111=mdb.models['Model-1'].rootAssembly.surfaces['b4']
r112=mdb.models['Model-1'].rootAssembly.instances['kpr-u'].surfaces['b1']
r121=mdb.models['Model-1'].rootAssembly.surfaces['b5']
r122=mdb.models['Model-1'].rootAssembly.instances['ep'].surfaces['b1']
r131=mdb.models['Model-1'].rootAssembly.surfaces['b5']
r132=mdb.models['Model-1'].rootAssembly.instances['kpl-d'].surfaces['b1']
r141=mdb.models['Model-1'].rootAssembly.surfaces['b5']
r142=mdb.models['Model-1'].rootAssembly.instances['kpr-d'].surfaces['b1']
r151=mdb.models['Model-1'].rootAssembly.surfaces['b6']
r152=mdb.models['Model-1'].rootAssembly.instances['b'].surfaces['b5']
r161=mdb.models['Model-1'].rootAssembly.surfaces['b6']
r162=mdb.models['Model-1'].rootAssembly.instances['sp'].surfaces['b1']
mdb.models['Model-1'].interactions['Int-1'].includedPairs.setValuesInStep(
stepName='Initial', useAllstar=OFF, addPairs=((r11, r12), (r21, r22), (
r31, r32), (r41, r42), (r51, r52), (r61, r62), (r71, r72), (r81, r82), 
(r91, r92), (r101, r102), (r111, r112), (r121, r122), (r131, r132), (
r141, r142), (r151, r152), (r161, r162)))
mdb.models['Model-1'].interactions['Int-1'].contactPropertyAssignments.appendInStep(
stepName='Initial', assignments=((GLOBAL, SELF, 'IntProp-1'), ))






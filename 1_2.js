var x = {
	"_type": "Project",
	"_id": "AAAAAAFF+h6SjaM2Hec=",
	"name": "Untitled",
	"ownedElements": [
		{
			"_type": "FCFlowchart",
			"_id": "AAAAAAFdq1DxJfw5Xio=",
			"_parent": {
				"$ref": "AAAAAAFF+h6SjaM2Hec="
			},
			"name": "Flowchart1",
			"ownedElements": [
				{
					"_type": "FCFlowchartDiagram",
					"_id": "AAAAAAFdq1DxJfw6eDM=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "排课V1.2流程",
					"visible": true,
					"defaultDiagram": false,
					"ownedViews": [
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFdq1D/ZvxACO8=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq1D/Zfw+h3I="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFdq1D/Z/xBsJc=",
									"_parent": {
										"$ref": "AAAAAAFdq1D/ZvxACO8="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Times New Roman;20;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 570,
									"top": 130,
									"width": 80,
									"height": 20,
									"autoResize": false,
									"underline": false,
									"text": "准备数据",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Times New Roman;20;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 560,
							"top": 120,
							"width": 100,
							"height": 40,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFdq1D/Z/xBsJc="
							},
							"wordWrap": true
						},
						{
							"_type": "UMLNoteView",
							"_id": "AAAAAAFdq1I8ifxg0OA=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Source Code Pro;13;0",
							"showShadow": false,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 744,
							"top": 56,
							"width": 150,
							"height": 40,
							"autoResize": false,
							"text": "准备预排，禁排，合班\n授课计划，场地数据",
							"wordWrap": false
						},
						{
							"_type": "UMLNoteLinkView",
							"_id": "AAAAAAFdq1I8kPxj13Q=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq1I8ifxg0OA="
							},
							"tail": {
								"$ref": "AAAAAAFdq1D/ZvxACO8="
							},
							"lineStyle": 1,
							"points": "660:123;749:96"
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFdq1ThsPyM/JE=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq1Thr/yKsMY="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFdq1ThsPyN8aY=",
									"_parent": {
										"$ref": "AAAAAAFdq1ThsPyM/JE="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;20;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 570,
									"top": 234,
									"width": 80,
									"height": 20,
									"autoResize": false,
									"underline": false,
									"text": "检查数据",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;20;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 560,
							"top": 224,
							"width": 100,
							"height": 40,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFdq1ThsPyN8aY="
							},
							"wordWrap": true
						},
						{
							"_type": "UMLNoteView",
							"_id": "AAAAAAFdq1VroPyWJmE=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 760,
							"top": 216,
							"width": 121,
							"height": 57,
							"autoResize": false,
							"text": "检查总课时数\n检查老师课时数\n检查场地数",
							"wordWrap": false
						},
						{
							"_type": "UMLNoteLinkView",
							"_id": "AAAAAAFdq1VrpvyZg7U=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq1VroPyWJmE="
							},
							"tail": {
								"$ref": "AAAAAAFdq1ThsPyM/JE="
							},
							"lineStyle": 1,
							"points": "660:243;759:244"
						},
						{
							"_type": "UMLNoteView",
							"_id": "AAAAAAFdq8a/V/zqe4M=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 744,
							"top": 128,
							"width": 137,
							"height": 50,
							"autoResize": false,
							"text": "将数据转换为\n算法需要的数据结构",
							"wordWrap": true
						},
						{
							"_type": "UMLNoteLinkView",
							"_id": "AAAAAAFdq8a/Wfztd60=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq8a/V/zqe4M="
							},
							"tail": {
								"$ref": "AAAAAAFdq1D/ZvxACO8="
							},
							"lineStyle": 1,
							"points": "660:142;743:148"
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFdq8e2Of0ZnmY=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq8e2OP0XAIU="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFdq8e2Of0aWF8=",
									"_parent": {
										"$ref": "AAAAAAFdq8e2Of0ZnmY="
									},
									"model": {
										"$ref": "AAAAAAFdq8e2OP0XAIU="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 623,
									"top": 184,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFdq8e2Of0ZnmY="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq1ThsPyM/JE="
							},
							"tail": {
								"$ref": "AAAAAAFdq1D/ZvxACO8="
							},
							"lineStyle": 2,
							"points": "609:159;609:224",
							"nameLabel": {
								"$ref": "AAAAAAFdq8e2Of0aWF8="
							}
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFdq8jK8P0s9Z8=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq8jK8P0qvqs="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFdq8jK8P0tbcc=",
									"_parent": {
										"$ref": "AAAAAAFdq8jK8P0s9Z8="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;20;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 570,
									"top": 343,
									"width": 80,
									"height": 20,
									"autoResize": false,
									"underline": false,
									"text": "开始排课",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;20;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 560,
							"top": 333,
							"width": 100,
							"height": 40,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFdq8jK8P0tbcc="
							},
							"wordWrap": true
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFdq8jLIf00VVk=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq8jLIf0yYqk="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFdq8jLIf01Iao=",
									"_parent": {
										"$ref": "AAAAAAFdq8jLIf00VVk="
									},
									"model": {
										"$ref": "AAAAAAFdq8jLIf0yYqk="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 622,
									"top": 291,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFdq8jLIf00VVk="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq8jK8P0s9Z8="
							},
							"tail": {
								"$ref": "AAAAAAFdq1ThsPyM/JE="
							},
							"lineStyle": 2,
							"points": "608:263;608:333",
							"nameLabel": {
								"$ref": "AAAAAAFdq8jLIf01Iao="
							}
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFdq835Af1bI9c=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq835Af1ZNUU="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFdq835Af1cT/w=",
									"_parent": {
										"$ref": "AAAAAAFdq835Af1bI9c="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;20;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 570,
									"top": 546,
									"width": 85,
									"height": 20,
									"autoResize": false,
									"underline": false,
									"text": "择优排课",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;20;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 560,
							"top": 536,
							"width": 105,
							"height": 40,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFdq835Af1cT/w="
							},
							"wordWrap": true
						},
						{
							"_type": "UMLNoteView",
							"_id": "AAAAAAFdq89akP1yZjs=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 792,
							"top": 424,
							"width": 202,
							"height": 70,
							"autoResize": false,
							"text": "优先合班配置\n择优选择授课计划\n根据授课计划随机获取课时\n安排课程",
							"wordWrap": true
						},
						{
							"_type": "UMLNoteLinkView",
							"_id": "AAAAAAFdq89ak/112J4=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq89akP1yZjs="
							},
							"tail": {
								"$ref": "AAAAAAFdq835Af1bI9c="
							},
							"lineStyle": 1,
							"points": "665:537;791:493"
						},
						{
							"_type": "UMLNoteView",
							"_id": "AAAAAAFdq9EvIf2hT8w=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 808,
							"top": 560,
							"width": 129,
							"height": 41,
							"autoResize": false,
							"text": "暂存课表，打分\n多个课表取最高分",
							"wordWrap": true
						},
						{
							"_type": "UMLNoteLinkView",
							"_id": "AAAAAAFdq9EvKP2kU3Y=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq9EvIf2hT8w="
							},
							"tail": {
								"$ref": "AAAAAAFdq9Q5gP2zaNc="
							},
							"lineStyle": 1,
							"points": "660:650;809:601"
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFdq9Q5gP2zaNc=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq9Q5gP2xfiM="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFdq9Q5gP20h2c=",
									"_parent": {
										"$ref": "AAAAAAFdq9Q5gP2zaNc="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;20;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 570,
									"top": 658,
									"width": 80,
									"height": 20,
									"autoResize": false,
									"underline": false,
									"text": "暂存课表",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;20;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 560,
							"top": 648,
							"width": 100,
							"height": 40,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFdq9Q5gP20h2c="
							},
							"wordWrap": true
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFdq9hklP4/Ob4=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq9hkk/49bJs="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFdq9hklf5An5k=",
									"_parent": {
										"$ref": "AAAAAAFdq9hklP4/Ob4="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;20;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 562,
									"top": 442,
									"width": 100,
									"height": 20,
									"autoResize": false,
									"underline": false,
									"text": "设定时间段",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;20;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 552,
							"top": 432,
							"width": 120,
							"height": 40,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFdq9hklf5An5k="
							},
							"wordWrap": true
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFdq9q0Y/54RcI=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq9q0Yf52MjA="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFdq9q0Y/55YNQ=",
									"_parent": {
										"$ref": "AAAAAAFdq9q0Y/54RcI="
									},
									"model": {
										"$ref": "AAAAAAFdq9q0Yf52MjA="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 623,
									"top": 395,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFdq9q0Y/54RcI="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq9hklP4/Ob4="
							},
							"tail": {
								"$ref": "AAAAAAFdq8jK8P0s9Z8="
							},
							"lineStyle": 2,
							"points": "609:372;609:432",
							"nameLabel": {
								"$ref": "AAAAAAFdq9q0Y/55YNQ="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFdq9q/gP6B/Xs=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq9q/f/5/Hh0="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFdq9q/gP6CT1A=",
									"_parent": {
										"$ref": "AAAAAAFdq9q/gP6B/Xs="
									},
									"model": {
										"$ref": "AAAAAAFdq9q/f/5/Hh0="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 625,
									"top": 496,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFdq9q/gP6B/Xs="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq835Af1bI9c="
							},
							"tail": {
								"$ref": "AAAAAAFdq9hklP4/Ob4="
							},
							"lineStyle": 2,
							"points": "611:471;611:536",
							"nameLabel": {
								"$ref": "AAAAAAFdq9q/gP6CT1A="
							}
						},
						{
							"_type": "FCTerminatorView",
							"_id": "AAAAAAFdq+B6wv6SHp0=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFdq+B6wf6Qzbw="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFdq+B6wv6TMRs=",
									"_parent": {
										"$ref": "AAAAAAFdq+B6wv6SHp0="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;20;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 570,
									"top": 762,
									"width": 80,
									"height": 20,
									"autoResize": false,
									"underline": false,
									"text": "排课完毕",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;20;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 560,
							"top": 752,
							"width": 100,
							"height": 40,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFdq+B6wv6TMRs="
							},
							"wordWrap": true
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFd1Iv4pqigwFs=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFd1Iv4pqiegac="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFd1Iv4pqihRcU=",
									"_parent": {
										"$ref": "AAAAAAFd1Iv4pqigwFs="
									},
									"model": {
										"$ref": "AAAAAAFd1Iv4pqiegac="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 625,
									"top": 604,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFd1Iv4pqigwFs="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq9Q5gP2zaNc="
							},
							"tail": {
								"$ref": "AAAAAAFdq835Af1bI9c="
							},
							"lineStyle": 2,
							"points": "611:575;611:648",
							"nameLabel": {
								"$ref": "AAAAAAFd1Iv4pqihRcU="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFd1IwDG6ipJY8=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"model": {
								"$ref": "AAAAAAFd1IwDGqinaxA="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFd1IwDHKiq7nQ=",
									"_parent": {
										"$ref": "AAAAAAFd1IwDG6ipJY8="
									},
									"model": {
										"$ref": "AAAAAAFd1IwDGqinaxA="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 623,
									"top": 712,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFd1IwDG6ipJY8="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFdq+B6wv6SHp0="
							},
							"tail": {
								"$ref": "AAAAAAFdq9Q5gP2zaNc="
							},
							"lineStyle": 2,
							"points": "609:687;609:752",
							"nameLabel": {
								"$ref": "AAAAAAFd1IwDHKiq7nQ="
							}
						},
						{
							"_type": "UMLNoteView",
							"_id": "AAAAAAFd1Iw3Eqiwgkw=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 744,
							"top": 712,
							"width": 124,
							"height": 50,
							"autoResize": false,
							"text": "展示得分最高课表",
							"wordWrap": true
						},
						{
							"_type": "UMLNoteLinkView",
							"_id": "AAAAAAFd1Iw3FqizSBY=",
							"_parent": {
								"$ref": "AAAAAAFdq1DxJfw6eDM="
							},
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFd1Iw3Eqiwgkw="
							},
							"tail": {
								"$ref": "AAAAAAFdq+B6wv6SHp0="
							},
							"lineStyle": 1,
							"points": "660:762;743:747"
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFdq1D/Zfw+h3I=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "准备数据",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFdq8e2OP0XAIU=",
							"_parent": {
								"$ref": "AAAAAAFdq1D/Zfw+h3I="
							},
							"source": {
								"$ref": "AAAAAAFdq1D/Zfw+h3I="
							},
							"target": {
								"$ref": "AAAAAAFdq1Thr/yKsMY="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFdq1Thr/yKsMY=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "检查数据",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFdq8jLIf0yYqk=",
							"_parent": {
								"$ref": "AAAAAAFdq1Thr/yKsMY="
							},
							"source": {
								"$ref": "AAAAAAFdq1Thr/yKsMY="
							},
							"target": {
								"$ref": "AAAAAAFdq8jK8P0qvqs="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFdq8jK8P0qvqs=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "开始排课",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFdq9q0Yf52MjA=",
							"_parent": {
								"$ref": "AAAAAAFdq8jK8P0qvqs="
							},
							"source": {
								"$ref": "AAAAAAFdq8jK8P0qvqs="
							},
							"target": {
								"$ref": "AAAAAAFdq9hkk/49bJs="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFdq835Af1ZNUU=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "择优排课",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFd1Iv4pqiegac=",
							"_parent": {
								"$ref": "AAAAAAFdq835Af1ZNUU="
							},
							"source": {
								"$ref": "AAAAAAFdq835Af1ZNUU="
							},
							"target": {
								"$ref": "AAAAAAFdq9Q5gP2xfiM="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFdq9Q5gP2xfiM=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "暂存课表",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFd1IwDGqinaxA=",
							"_parent": {
								"$ref": "AAAAAAFdq9Q5gP2xfiM="
							},
							"source": {
								"$ref": "AAAAAAFdq9Q5gP2xfiM="
							},
							"target": {
								"$ref": "AAAAAAFdq+B6wf6Qzbw="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFdq9hkk/49bJs=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "设定时间段",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFdq9q/f/5/Hh0=",
							"_parent": {
								"$ref": "AAAAAAFdq9hkk/49bJs="
							},
							"source": {
								"$ref": "AAAAAAFdq9hkk/49bJs="
							},
							"target": {
								"$ref": "AAAAAAFdq835Af1ZNUU="
							}
						}
					]
				},
				{
					"_type": "FCTerminator",
					"_id": "AAAAAAFdq+B6wf6Qzbw=",
					"_parent": {
						"$ref": "AAAAAAFdq1DxJfw5Xio="
					},
					"name": "排课完毕"
				}
			]
		},
		{
			"_type": "FCFlowchart",
			"_id": "AAAAAAFebnrPAgKg8/0=",
			"_parent": {
				"$ref": "AAAAAAFF+h6SjaM2Hec="
			},
			"name": "Flowchart2",
			"ownedElements": [
				{
					"_type": "FCFlowchartDiagram",
					"_id": "AAAAAAFebnrPAgKhqE8=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "排课V1.3流程",
					"visible": true,
					"defaultDiagram": false,
					"ownedViews": [
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFebnrrWAKn5Tc=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebnrrVwKlIAU="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebnrrWAKoSCg=",
									"_parent": {
										"$ref": "AAAAAAFebnrrWAKn5Tc="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 306,
									"top": 394,
									"width": 286.27734375,
									"height": 18,
									"autoResize": false,
									"underline": false,
									"text": "加载数据-->处理数据-->检查数据",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 296,
							"top": 384,
							"width": 306.27734375,
							"height": 41,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebnrrWAKoSCg="
							},
							"wordWrap": true
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFebnwBiALGhuY=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebnwBhgLELXE="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebnwBiALHIgI=",
									"_parent": {
										"$ref": "AAAAAAFebnwBiALGhuY="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 634,
									"top": 450,
									"width": 72,
									"height": 18,
									"autoResize": false,
									"underline": false,
									"text": "拆取组合",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 624,
							"top": 440,
							"width": 92,
							"height": 38,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebnwBiALHIgI="
							},
							"wordWrap": true
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFebnyu/gLRa+U=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebnyu/gLPWa0="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebnyvAQLSaZ4=",
									"_parent": {
										"$ref": "AAAAAAFebnyu/gLRa+U="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 634,
									"top": 602,
									"width": 72,
									"height": 36,
									"autoResize": false,
									"underline": false,
									"text": "根据组合 分散到天",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 624,
							"top": 592,
							"width": 92,
							"height": 56,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebnyvAQLSaZ4="
							},
							"wordWrap": true
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFeboIz3gLs/GQ=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFeboIz3QLqvDw="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFeboIz3gLtZNU=",
									"_parent": {
										"$ref": "AAAAAAFeboIz3gLs/GQ="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 618,
									"top": 522,
									"width": 109,
									"height": 18,
									"autoResize": false,
									"underline": false,
									"text": "多线程处理",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 608,
							"top": 512,
							"width": 129,
							"height": 38,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFeboIz3gLtZNU="
							},
							"wordWrap": true
						},
						{
							"_type": "FCDecisionView",
							"_id": "AAAAAAFeboW+mAL6Z7M=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFeboW+lwL48zo="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFeboW+mQL7BDc=",
									"_parent": {
										"$ref": "AAAAAAFeboW+mAL6Z7M="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 598.25,
									"top": 724,
									"width": 140.5,
									"height": 40,
									"autoResize": false,
									"underline": false,
									"text": "检查天组合 可靠性",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 528,
							"top": 704,
							"width": 281,
							"height": 80,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFeboW+mQL7BDc="
							},
							"wordWrap": true
						},
						{
							"_type": "FCDecisionView",
							"_id": "AAAAAAFeboeYjwMqtnk=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFeboeYjgMoGSA="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFeboeYjwMr1P4=",
									"_parent": {
										"$ref": "AAAAAAFeboeYjwMqtnk="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 587.50048828125,
									"top": 860.25,
									"width": 167.0009765625,
									"height": 40.5,
									"autoResize": false,
									"underline": false,
									"text": "将天组合 分配到课时",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 504,
							"top": 840,
							"width": 334.001953125,
							"height": 81,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFeboeYjwMr1P4="
							},
							"wordWrap": true
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebqDGPgP/2uQ=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebqDGPgP9MJg="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebqDGPgQAYyI=",
									"_parent": {
										"$ref": "AAAAAAFebqDGPgP/2uQ="
									},
									"model": {
										"$ref": "AAAAAAFebqDGPgP9MJg="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 684,
									"top": 487,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebqDGPgP/2uQ="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFeboIz3gLs/GQ="
							},
							"tail": {
								"$ref": "AAAAAAFebnwBiALGhuY="
							},
							"lineStyle": 2,
							"points": "670:477;670:512",
							"nameLabel": {
								"$ref": "AAAAAAFebqDGPgQAYyI="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebqDSGQQI+uQ=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebqDSGQQGX5w="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebqDSGQQJdiM=",
									"_parent": {
										"$ref": "AAAAAAFebqDSGQQI+uQ="
									},
									"model": {
										"$ref": "AAAAAAFebqDSGQQGX5w="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 685,
									"top": 563,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebqDSGQQI+uQ="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebnyu/gLRa+U="
							},
							"tail": {
								"$ref": "AAAAAAFeboIz3gLs/GQ="
							},
							"lineStyle": 2,
							"points": "671:549;671:592",
							"nameLabel": {
								"$ref": "AAAAAAFebqDSGQQJdiM="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebqDfBgQRVwo=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebqDfBQQPpaI="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebqDfBgQS9vs=",
									"_parent": {
										"$ref": "AAAAAAFebqDfBgQRVwo="
									},
									"model": {
										"$ref": "AAAAAAFebqDfBQQPpaI="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 683,
									"top": 668,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebqDfBgQRVwo="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFeboW+mAL6Z7M="
							},
							"tail": {
								"$ref": "AAAAAAFebnyu/gLRa+U="
							},
							"lineStyle": 2,
							"points": "669:647;669:704",
							"nameLabel": {
								"$ref": "AAAAAAFebqDfBgQS9vs="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebqDrHgQawAg=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebqDrHQQYBMc="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebqDrHgQb+78=",
									"_parent": {
										"$ref": "AAAAAAFebqDrHgQawAg="
									},
									"model": {
										"$ref": "AAAAAAFebqDrHQQYBMc="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 682,
									"top": 804,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebqDrHgQawAg="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFeboeYjwMqtnk="
							},
							"tail": {
								"$ref": "AAAAAAFeboW+mAL6Z7M="
							},
							"lineStyle": 2,
							"points": "668:783;668:840",
							"nameLabel": {
								"$ref": "AAAAAAFebqDrHgQb+78="
							}
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFebrPPIgQjMrE=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebrPPIQQh/gY="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebrPPIgQkTJ8=",
									"_parent": {
										"$ref": "AAAAAAFebrPPIgQjMrE="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 610,
									"top": 994,
									"width": 117,
									"height": 18,
									"autoResize": false,
									"underline": false,
									"text": "暂存数据",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 600,
							"top": 984,
							"width": 137,
							"height": 38,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebrPPIgQkTJ8="
							},
							"wordWrap": true
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFebrgIOgRFXQo=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebrgIOQRDvzw="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebrgIOgRGDj0=",
									"_parent": {
										"$ref": "AAAAAAFebrgIOgRFXQo="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 914,
									"top": 1010,
									"width": 108,
									"height": 18,
									"autoResize": false,
									"underline": false,
									"text": "记录原因",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 904,
							"top": 1000,
							"width": 128,
							"height": 41,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebrgIOgRGDj0="
							},
							"wordWrap": true
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebroKqARTWQo=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebroKqARREBk="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebroKqQRUias=",
									"_parent": {
										"$ref": "AAAAAAFebroKqARTWQo="
									},
									"model": {
										"$ref": "AAAAAAFebroKqARREBk="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": false,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 864,
									"top": 752,
									"width": 91,
									"height": 13,
									"autoResize": true,
									"alpha": -1.33781708870577,
									"distance": 60.63827174318213,
									"hostEdge": {
										"$ref": "AAAAAAFebroKqARTWQo="
									},
									"edgePosition": 1,
									"underline": false,
									"text": "不满足部分条件",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebrgIOgRFXQo="
							},
							"tail": {
								"$ref": "AAAAAAFeboW+mAL6Z7M="
							},
							"lineStyle": 2,
							"points": "808:744;968:744;968:1000",
							"nameLabel": {
								"$ref": "AAAAAAFebroKqQRUias="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebrsMgAR4X1o=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebrsMfwR2dUc="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebrsMgAR5Ixk=",
									"_parent": {
										"$ref": "AAAAAAFebrsMgAR4X1o="
									},
									"model": {
										"$ref": "AAAAAAFebrsMfwR2dUc="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 839,
									"top": 888,
									"width": 52,
									"height": 13,
									"autoResize": true,
									"alpha": -1.2261490129726196,
									"distance": 41.43669871020132,
									"hostEdge": {
										"$ref": "AAAAAAFebrsMgAR4X1o="
									},
									"edgePosition": 1,
									"underline": false,
									"text": "分配异常",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebrgIOgRFXQo="
							},
							"tail": {
								"$ref": "AAAAAAFeboeYjwMqtnk="
							},
							"lineStyle": 2,
							"points": "837:880;904:880;904:1000",
							"nameLabel": {
								"$ref": "AAAAAAFebrsMgAR5Ixk="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebr08gQSZGpQ=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebr08gASXfD0="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebr08ggSaKuM=",
									"_parent": {
										"$ref": "AAAAAAFebr08gQSZGpQ="
									},
									"model": {
										"$ref": "AAAAAAFebr08gASXfD0="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 643,
									"top": 945,
									"width": 78,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebr08gQSZGpQ="
									},
									"edgePosition": 1,
									"underline": false,
									"text": "成功产生课表",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebrPPIgQjMrE="
							},
							"tail": {
								"$ref": "AAAAAAFeboeYjwMqtnk="
							},
							"lineStyle": 2,
							"points": "668:920;668:984",
							"nameLabel": {
								"$ref": "AAAAAAFebr08ggSaKuM="
							}
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFebr7YRwTMuug=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebr7YRwTKfnU="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebr7YRwTNT6g=",
									"_parent": {
										"$ref": "AAAAAAFebr7YRwTMuug="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 818,
									"top": 1194,
									"width": 143,
									"height": 36,
									"autoResize": false,
									"underline": false,
									"text": "返回出现次数 最多的三条原因",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 808,
							"top": 1184,
							"width": 163,
							"height": 56,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebr7YRwTNT6g="
							},
							"wordWrap": true
						},
						{
							"_type": "FCProcessView",
							"_id": "AAAAAAFebsGvMAUeP2E=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebsGvLwUcJfo="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebsGvMAUfK1k=",
									"_parent": {
										"$ref": "AAAAAAFebsGvMAUeP2E="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 634,
									"top": 1202,
									"width": 72,
									"height": 18,
									"autoResize": false,
									"underline": false,
									"text": "返回课表",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 624,
							"top": 1192,
							"width": 92,
							"height": 38,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebsGvMAUfK1k="
							},
							"wordWrap": true
						},
						{
							"_type": "FCDecisionView",
							"_id": "AAAAAAFebsezawWBVD0=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebsezagV/QlA="
							},
							"subViews": [
								{
									"_type": "LabelView",
									"_id": "AAAAAAFebsezbAWC7yM=",
									"_parent": {
										"$ref": "AAAAAAFebsezawWBVD0="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;18;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 614,
									"top": 1096.25,
									"width": 108,
									"height": 32.5,
									"autoResize": false,
									"underline": false,
									"text": "获取最优课表",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": true
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;18;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"left": 560,
							"top": 1080,
							"width": 216,
							"height": 65,
							"autoResize": false,
							"nameLabel": {
								"$ref": "AAAAAAFebsezbAWC7yM="
							},
							"wordWrap": true
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebsjIKQWurrc=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebsjIJwWsFCc="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebsjIKQWvHc4=",
									"_parent": {
										"$ref": "AAAAAAFebsjIKQWurrc="
									},
									"model": {
										"$ref": "AAAAAAFebsjIJwWsFCc="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 682,
									"top": 1043,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebsjIKQWurrc="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebsezawWBVD0="
							},
							"tail": {
								"$ref": "AAAAAAFebrPPIgQjMrE="
							},
							"lineStyle": 2,
							"points": "668:1021;668:1080",
							"nameLabel": {
								"$ref": "AAAAAAFebsjIKQWvHc4="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebsjd3wW3mYc=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebsjd3gW15CU="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebsjd3wW40ug=",
									"_parent": {
										"$ref": "AAAAAAFebsjd3wW3mYc="
									},
									"model": {
										"$ref": "AAAAAAFebsjd3gW15CU="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 681,
									"top": 1161,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebsjd3wW3mYc="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebsGvMAUeP2E="
							},
							"tail": {
								"$ref": "AAAAAAFebsezawWBVD0="
							},
							"lineStyle": 2,
							"points": "667:1144;667:1192",
							"nameLabel": {
								"$ref": "AAAAAAFebsjd3wW40ug="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebsjp5wXA9lE=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebsjp5wW+2BY="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebsjp5wXBElI=",
									"_parent": {
										"$ref": "AAAAAAFebsjp5wXA9lE="
									},
									"model": {
										"$ref": "AAAAAAFebsjp5wW+2BY="
									},
									"visible": true,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 824,
									"top": 1160,
									"width": 65,
									"height": 13,
									"autoResize": false,
									"alpha": -0.5349550617302479,
									"distance": 62.76941930590086,
									"hostEdge": {
										"$ref": "AAAAAAFebsjp5wXA9lE="
									},
									"edgePosition": 1,
									"underline": false,
									"text": "无可用课表",
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebr7YRwTMuug="
							},
							"tail": {
								"$ref": "AAAAAAFebsezawWBVD0="
							},
							"lineStyle": 2,
							"points": "775:1112;888:1112;888:1184",
							"nameLabel": {
								"$ref": "AAAAAAFebsjp5wXBElI="
							}
						},
						{
							"_type": "FCFlowView",
							"_id": "AAAAAAFebswSQAXqi+4=",
							"_parent": {
								"$ref": "AAAAAAFebnrPAgKhqE8="
							},
							"model": {
								"$ref": "AAAAAAFebswSQAXoLq8="
							},
							"subViews": [
								{
									"_type": "EdgeLabelView",
									"_id": "AAAAAAFebswSQQXrJIA=",
									"_parent": {
										"$ref": "AAAAAAFebswSQAXqi+4="
									},
									"model": {
										"$ref": "AAAAAAFebswSQAXoLq8="
									},
									"visible": false,
									"enabled": true,
									"lineColor": "#000000",
									"fillColor": "#ffffff",
									"fontColor": "#000000",
									"font": "Arial;13;0",
									"showShadow": true,
									"containerChangeable": false,
									"containerExtending": false,
									"left": 682,
									"top": 397,
									"width": 0,
									"height": 13,
									"autoResize": false,
									"alpha": 1.5707963267948966,
									"distance": 15,
									"hostEdge": {
										"$ref": "AAAAAAFebswSQAXqi+4="
									},
									"edgePosition": 1,
									"underline": false,
									"horizontalAlignment": 2,
									"verticalAlignment": 5,
									"wordWrap": false
								}
							],
							"visible": true,
							"enabled": true,
							"lineColor": "#000000",
							"fillColor": "#ffffff",
							"fontColor": "#000000",
							"font": "Arial;13;0",
							"showShadow": true,
							"containerChangeable": false,
							"containerExtending": false,
							"head": {
								"$ref": "AAAAAAFebnwBiALGhuY="
							},
							"tail": {
								"$ref": "AAAAAAFebnrrWAKn5Tc="
							},
							"lineStyle": 2,
							"points": "601:404;668:404;668:440",
							"nameLabel": {
								"$ref": "AAAAAAFebswSQQXrJIA="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFebnrrVwKlIAU=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "加载数据-->处理数据-->检查数据",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebswSQAXoLq8=",
							"_parent": {
								"$ref": "AAAAAAFebnrrVwKlIAU="
							},
							"source": {
								"$ref": "AAAAAAFebnrrVwKlIAU="
							},
							"target": {
								"$ref": "AAAAAAFebnwBhgLELXE="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFebnwBhgLELXE=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "拆取组合",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebqDGPgP9MJg=",
							"_parent": {
								"$ref": "AAAAAAFebnwBhgLELXE="
							},
							"source": {
								"$ref": "AAAAAAFebnwBhgLELXE="
							},
							"target": {
								"$ref": "AAAAAAFeboIz3QLqvDw="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFebnyu/gLPWa0=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "根据组合 分散到天",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebqDfBQQPpaI=",
							"_parent": {
								"$ref": "AAAAAAFebnyu/gLPWa0="
							},
							"source": {
								"$ref": "AAAAAAFebnyu/gLPWa0="
							},
							"target": {
								"$ref": "AAAAAAFeboW+lwL48zo="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFeboIz3QLqvDw=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "多线程处理",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebqDSGQQGX5w=",
							"_parent": {
								"$ref": "AAAAAAFeboIz3QLqvDw="
							},
							"source": {
								"$ref": "AAAAAAFeboIz3QLqvDw="
							},
							"target": {
								"$ref": "AAAAAAFebnyu/gLPWa0="
							}
						}
					]
				},
				{
					"_type": "FCDecision",
					"_id": "AAAAAAFeboW+lwL48zo=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "检查天组合 可靠性",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebqDrHQQYBMc=",
							"_parent": {
								"$ref": "AAAAAAFeboW+lwL48zo="
							},
							"source": {
								"$ref": "AAAAAAFeboW+lwL48zo="
							},
							"target": {
								"$ref": "AAAAAAFeboeYjgMoGSA="
							}
						},
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebroKqARREBk=",
							"_parent": {
								"$ref": "AAAAAAFeboW+lwL48zo="
							},
							"name": "不满足部分条件",
							"source": {
								"$ref": "AAAAAAFeboW+lwL48zo="
							},
							"target": {
								"$ref": "AAAAAAFebrgIOQRDvzw="
							}
						}
					]
				},
				{
					"_type": "FCDecision",
					"_id": "AAAAAAFeboeYjgMoGSA=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "将天组合 分配到课时",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebrsMfwR2dUc=",
							"_parent": {
								"$ref": "AAAAAAFeboeYjgMoGSA="
							},
							"name": "分配异常",
							"source": {
								"$ref": "AAAAAAFeboeYjgMoGSA="
							},
							"target": {
								"$ref": "AAAAAAFebrgIOQRDvzw="
							}
						},
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebr08gASXfD0=",
							"_parent": {
								"$ref": "AAAAAAFeboeYjgMoGSA="
							},
							"name": "成功产生课表",
							"source": {
								"$ref": "AAAAAAFeboeYjgMoGSA="
							},
							"target": {
								"$ref": "AAAAAAFebrPPIQQh/gY="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFebrPPIQQh/gY=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "暂存数据",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebsjIJwWsFCc=",
							"_parent": {
								"$ref": "AAAAAAFebrPPIQQh/gY="
							},
							"source": {
								"$ref": "AAAAAAFebrPPIQQh/gY="
							},
							"target": {
								"$ref": "AAAAAAFebsezagV/QlA="
							}
						}
					]
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFebrgIOQRDvzw=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "记录原因"
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFebr7YRwTKfnU=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "返回出现次数 最多的三条原因"
				},
				{
					"_type": "FCProcess",
					"_id": "AAAAAAFebsGvLwUcJfo=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "返回课表"
				},
				{
					"_type": "FCDecision",
					"_id": "AAAAAAFebsezagV/QlA=",
					"_parent": {
						"$ref": "AAAAAAFebnrPAgKg8/0="
					},
					"name": "获取最优课表",
					"ownedElements": [
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebsjd3gW15CU=",
							"_parent": {
								"$ref": "AAAAAAFebsezagV/QlA="
							},
							"source": {
								"$ref": "AAAAAAFebsezagV/QlA="
							},
							"target": {
								"$ref": "AAAAAAFebsGvLwUcJfo="
							}
						},
						{
							"_type": "FCFlow",
							"_id": "AAAAAAFebsjp5wW+2BY=",
							"_parent": {
								"$ref": "AAAAAAFebsezagV/QlA="
							},
							"name": "无可用课表",
							"source": {
								"$ref": "AAAAAAFebsezagV/QlA="
							},
							"target": {
								"$ref": "AAAAAAFebr7YRwTKfnU="
							}
						}
					]
				}
			]
		}
	]
}
-- 기존 데이터를 모두 삭제합니다.
DELETE FROM reactions;
DELETE FROM messages;
DELETE FROM recipients;

-- 30개의 recipient 생성 (배경 이미지 URL 추가)
INSERT INTO recipients (name, background_color, background_image_url, created_at) VALUES
('김민준', 'BEIGE', 'https://picsum.photos/id/683/3840/2160', '2024-12-15'),
('이서연', 'PURPLE', NULL, '2024-12-20'),
('박지훈', 'GREEN', 'https://picsum.photos/id/24/3840/2160', '2025-01-05'),
('최수아', 'BLUE', NULL, '2025-01-10'),
('정민서', 'BEIGE', 'https://picsum.photos/id/599/3840/2160', '2025-01-15'),
('강지우', 'PURPLE', NULL, '2025-01-20'),
('윤서준', 'GREEN', 'https://picsum.photos/id/1058/3840/2160', '2025-01-25'),
('조예은', 'BLUE', NULL, '2025-02-01'),
('한도윤', 'BEIGE', 'https://picsum.photos/id/683/3840/2160', '2025-02-05'),
('신하은', 'PURPLE', NULL, '2025-02-10'),
('임준호', 'GREEN', 'https://picsum.photos/id/24/3840/2160', '2025-02-15'),
('송지아', 'BLUE', NULL, '2025-02-20'),
('오세준', 'BEIGE', 'https://picsum.photos/id/599/3840/2160', '2025-02-25'),
('이지현', 'PURPLE', NULL, '2025-03-01'),
('권현우', 'GREEN', 'https://picsum.photos/id/1058/3840/2160', '2025-03-05'),
('황수민', 'BLUE', NULL, '2025-03-10'),
('고동현', 'BEIGE', 'https://picsum.photos/id/683/3840/2160', '2025-03-15'),
('장미래', 'PURPLE', NULL, '2025-03-20'),
('류태양', 'GREEN', 'https://picsum.photos/id/24/3840/2160', '2025-03-25'),
('배소율', 'BLUE', NULL, '2025-03-30'),
('전지민', 'BEIGE', 'https://picsum.photos/id/599/3840/2160', '2024-12-25'),
('노유진', 'PURPLE', NULL, '2024-12-30'),
('문승현', 'GREEN', 'https://picsum.photos/id/1058/3840/2160', '2025-01-02'),
('안하늘', 'BLUE', NULL, '2025-01-07'),
('양준서', 'BEIGE', 'https://picsum.photos/id/683/3840/2160', '2025-01-12'),
('남도희', 'PURPLE', NULL, '2025-01-17'),
('곽우진', 'GREEN', 'https://picsum.photos/id/24/3840/2160', '2025-01-22'),
('구예린', 'BLUE', NULL, '2025-01-27'),
('홍준영', 'BEIGE', 'https://picsum.photos/id/599/3840/2160', '2025-02-03'),
('백서윤', 'PURPLE', 'https://picsum.photos/id/1058/3840/2160', '2025-02-08');

-- Recipient 1 메시지 (프로필 이미지 URL 추가 및 relationship 수정)
INSERT INTO messages (recipient_id, sender, profile_image_url, background_color, relationship, content, font, created_at) VALUES
(1, '친구1', 'https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png', 'beige', 'FRIEND', '[{"type":"heading-one","children":[{"text":"항상 응원할게! 파이팅!"}]}]', NULL, '2024-12-15'),
(1, '동료1', 'https://picsum.photos/id/522/100/100', 'purple', 'COLLEAGUE', '[{"type":"paragraph","children":[{"text":"함께 일할 수 있어서 즐거웠어요. ","fontFamily":"Nanum Gothic"},{"text":"항상 도움을 주셔서 감사합니다.","bold":true,"fontFamily":"Nanum Gothic"}]}]', NULL, '2024-12-20'),
(1, '선생님', NULL, 'green', 'OTHER', '[{"type":"heading-two","children":[{"text":"열심히 하는 모습이 보기 좋습니다."}]},{"type":"paragraph","children":[{"text":"앞으로도 그 열정 잃지 마세요.","italic":true,"fontFamily":"sans-serif","color":"#0066CC"}]}]', NULL, '2025-01-05'),
(1, '이웃', 'https://picsum.photos/id/547/100/100', 'blue', 'OTHER', '[{"type":"paragraph","children":[{"text":"항상 밝은 모습으로 인사해주셔서 "},{"text":"저희 동네가 더 밝아집니다.","bold":true,"fontFamily":"sans-serif","color":"#FF7F00"}]}]', NULL, '2025-01-10'),
(1, '가족', 'https://picsum.photos/id/268/100/100', 'beige', 'FAMILY', '[{"type":"heading-one","children":[{"text":"우리 가족의 기둥이 되어줘서 고마워."}]},{"type":"paragraph","children":[{"text":"항상 사랑해.","fontFamily":"Nanum Gothic","color":"#FF0000"}]}]', NULL, '2025-01-15'),
(1, '후배', NULL, 'green', 'OTHER', '[{"type":"heading-two","children":[{"text":"늘 배움을 주셔서 감사합니다."}]},{"type":"paragraph","children":[{"text":"선배님 덕분에 많이 성장했어요!","bold":true,"fontFamily":"sans-serif","color":"#009900"}]}]', NULL, '2025-01-20'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"학창시절부터 지금까지 "},{"text":"변함없이 멋진 사람","italic":true,"fontFamily":"Nanum Gothic","color":"#0000FF"},{"text":"이라 자랑스러워!"}]}]', NULL, '2025-01-25'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너의 열정은 언제나 "},{"text":"빛나고 있어","bold":true,"fontFamily":"Nanum Gothic","color":"#FF4500"},{"text":"!"}]}]', NULL, '2025-01-26'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"우리가 함께한 시간들이 "},{"text":"소중한 추억","italic":true,"fontFamily":"Nanum Gothic","color":"#32CD32"},{"text":"으로 남아."}]}]', NULL, '2025-01-27'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너의 미소는 "},{"text":"항상 나를 기쁘게 해","bold":true,"fontFamily":"Nanum Gothic","color":"#1E90FF"},{"text":"."}]}]', NULL, '2025-01-28'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너와의 우정은 "},{"text":"평생의 보물","italic":true,"fontFamily":"Nanum Gothic","color":"#FFD700"},{"text":"이야."}]}]', NULL, '2025-01-29'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너의 성장은 "},{"text":"항상 나에게 영감을 줘","bold":true,"fontFamily":"Nanum Gothic","color":"#8A2BE2"},{"text":"."}]}]', NULL, '2025-01-30'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너의 진심은 "},{"text":"항상 느껴져","italic":true,"fontFamily":"Nanum Gothic","color":"#FF69B4"},{"text":"."}]}]', NULL, '2025-01-31'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너와 함께한 모든 순간이 "},{"text":"빛나","bold":true,"fontFamily":"Nanum Gothic","color":"#00FA9A"},{"text":"."}]}]', NULL, '2025-02-01'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너의 따뜻한 마음이 "},{"text":"항상 나를 감동시켜","italic":true,"fontFamily":"Nanum Gothic","color":"#FF6347"},{"text":"."}]}]', NULL, '2025-02-02'),
(1, '동창', 'https://picsum.photos/id/1082/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너의 존재가 "},{"text":"항상 나에게 힘이 돼","bold":true,"fontFamily":"Nanum Gothic","color":"#4682B4"},{"text":"."}]}]', NULL, '2025-02-03');

-- Recipient 2 메시지 (프로필 이미지 URL 추가 및 relationship 수정)
INSERT INTO messages (recipient_id, sender, profile_image_url, background_color, relationship, content, font, created_at) VALUES
(2, '친구1', 'https://picsum.photos/id/571/100/100', 'blue', 'FRIEND', '[{"type":"heading-two","children":[{"text":"너의 미소가 세상을 밝게 만들어."}]},{"type":"paragraph","children":[{"text":"항상 그 미소 잃지 마!","italic":true,"fontFamily":"sans-serif"}]}]', NULL, '2025-02-01'),
(2, '동료1', 'https://picsum.photos/id/494/100/100', 'beige', 'COLLEAGUE', '[{"type":"paragraph","children":[{"text":"당신의 ","fontFamily":"Nanum Gothic"},{"text":"창의적인 아이디어","bold":true,"fontFamily":"Nanum Gothic"},{"text":"가 우리 팀을 성장시켰어요.","fontFamily":"Nanum Gothic"}]}]', NULL, '2025-02-05'),
(2, '선배', NULL, 'purple', 'OTHER', '[{"type":"heading-one","children":[{"text":"항상 열심히 하는 모습이 멋져요."}]},{"type":"paragraph","children":[{"text":"앞으로도 응원할게요!","fontFamily":"sans-serif","color":"#6600CC"}]}]', NULL, '2025-02-10'),
(2, '친구2', 'https://picsum.photos/id/859/100/100', 'green', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너와 함께한 추억들이 "},{"text":"내 인생에서 가장 소중한 보물","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#008800"},{"text":"이야."}]}]', NULL, '2025-02-15'),
(2, '가족', 'https://picsum.photos/id/437/100/100', 'blue', 'FAMILY', '[{"type":"heading-one","children":[{"text":"우리 가족의 자랑이야."}]},{"type":"paragraph","children":[{"text":"항상 네 꿈을 응원할게.","fontFamily":"Nanum Gothic"}]}]', NULL, '2025-02-20'),
(2, '멘토', NULL, 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"네 성장이 눈부십니다."}]},{"type":"paragraph","children":[{"text":"더 큰 꿈을 향해 나아가세요!","bold":true,"fontFamily":"sans-serif","color":"#8A2BE2"}]}]', NULL, '2025-02-25'),
(2, '교수님', 'https://picsum.photos/id/1064/100/100', 'beige', 'OTHER', '[{"type":"paragraph","children":[{"text":"학문적 열정이 "},{"text":"미래를 밝게 비춰줄 것","bold":true,"fontFamily":"Nanum Gothic","color":"#654321"},{"text":"입니다."}]}]', NULL, '2025-03-01');

-- Recipient 3 메시지 (프로필 이미지 URL 추가 및 relationship 수정)
INSERT INTO messages (recipient_id, sender, profile_image_url, background_color, relationship, content, font, created_at) VALUES
(3, '팀원', 'https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png', 'green', 'COLLEAGUE', '[{"type":"heading-one","children":[{"text":"당신의 리더십은 우리의 나침반입니다."}]}]', NULL, '2025-03-05'),
(3, '후배', 'https://picsum.photos/id/522/100/100', 'blue', 'OTHER', '[{"type":"paragraph","children":[{"text":"선배님처럼 "},{"text":"열정적인 사람","bold":true,"fontFamily":"Nanum Gothic"},{"text":"이 되고 싶어요!"}]}]', NULL, '2025-03-10'),
(3, '고객', NULL, 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"항상 친절한 대응에 감사드립니다."}]},{"type":"paragraph","children":[{"text":"앞으로도 잘 부탁드려요.","italic":true,"fontFamily":"sans-serif","color":"#800080"}]}]', NULL, '2025-03-15'),
(3, '친구', 'https://picsum.photos/id/547/100/100', 'beige', 'FRIEND', '[{"type":"paragraph","children":[{"text":"20년지기 친구로서 "},{"text":"너의 꿈을 항상 응원할게","bold":true,"fontFamily":"sans-serif","color":"#A52A2A"}]}]', NULL, '2025-03-20'),
(3, '고모', 'https://picsum.photos/id/268/100/100', 'green', 'FAMILY', '[{"type":"heading-one","children":[{"text":"우리 조카 최고!"}]},{"type":"paragraph","children":[{"text":"고모가 항상 응원한다!","fontFamily":"Nanum Gothic","color":"#006400"}]}]', NULL, '2025-03-25'),
(3, '이웃주민', NULL, 'blue', 'OTHER', '[{"type":"paragraph","children":[{"text":"항상 도움 주셔서 "},{"text":"감사합니다","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#00008B"},{"text":"!"}]}]', NULL, '2025-03-30'),
(3, '동호회원', 'https://picsum.photos/id/1082/100/100', 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"당신과 함께하는 활동이 즐겁습니다."}]},{"type":"paragraph","children":[{"text":"앞으로도 좋은 인연 이어가요!","fontFamily":"Nanum Gothic"}]}]', NULL, '2024-12-25');

-- 추가 recipient 4-30에 대한 메시지 (일부 예시 - 프로필 이미지 URL 추가 및 relationship 수정)
INSERT INTO messages (recipient_id, sender, profile_image_url, background_color, relationship, content, font, created_at) VALUES
(4, '직장동료', 'https://picsum.photos/id/571/100/100', 'purple', 'COLLEAGUE', '[{"type":"heading-one","children":[{"text":"당신의 리더십에 항상 감탄합니다."}]},{"type":"paragraph","children":[{"text":"함께 일할 수 있어 영광이에요.","fontFamily":"Nanum Gothic","color":"#9932CC"}]}]', NULL, '2025-01-02'),
(4, '오랜친구', 'https://picsum.photos/id/494/100/100', 'beige', 'FRIEND', '[{"type":"paragraph","children":[{"text":"20년이 지나도 변함없는 "},{"text":"너의 따뜻한 마음","bold":true,"fontFamily":"sans-serif","color":"#8B4513"},{"text":"이 좋아."}]}]', NULL, '2025-01-07'),
(4, '제자', NULL, 'green', 'OTHER', '[{"type":"heading-two","children":[{"text":"선생님의 가르침이 제 인생의 등대입니다."}]},{"type":"paragraph","children":[{"text":"항상 감사드립니다.","italic":true,"fontFamily":"Nanum Gothic"}]}]', NULL, '2025-01-12'),
(5, '친구', 'https://picsum.photos/id/859/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너의 웃음이 "},{"text":"내 하루를 환하게 만들어","bold":true,"fontFamily":"sans-serif","color":"#4169E1"},{"text":"!"}]}]', NULL, '2025-01-17'),
(5, '후배', NULL, 'purple', 'OTHER', '[{"type":"heading-one","children":[{"text":"선배님처럼 되고 싶어요!"}]},{"type":"paragraph","children":[{"text":"언제나 롤모델입니다.","fontFamily":"Nanum Gothic","color":"#800080"}]}]', NULL, '2025-01-22'),
(5, '엄마', 'https://picsum.photos/id/437/100/100', 'beige', 'FAMILY', '[{"type":"heading-two","children":[{"text":"우리 딸, 너무 자랑스러워."}]},{"type":"paragraph","children":[{"text":"엄마가 항상 사랑하고 응원할게.","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#CD5C5C"}]}]', NULL, '2025-01-27'),
(6, '멘티', NULL, 'green', 'OTHER', '[{"type":"paragraph","children":[{"text":"멘토님이 있어 제 길을 "},{"text":"더 선명하게 볼 수 있습니다","bold":true,"fontFamily":"Nanum Gothic"},{"text":". 감사합니다!"}]}]', NULL, '2025-01-25'),
(6, '직장상사', 'https://picsum.photos/id/1064/100/100', 'blue', 'COLLEAGUE', '[{"type":"heading-one","children":[{"text":"당신의 성장이 회사의 빛입니다."}]},{"type":"paragraph","children":[{"text":"앞으로도 함께해요.","fontFamily":"sans-serif","color":"#000080"}]}]', NULL, '2025-02-01'),
(7, '동창', 'https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png', 'purple', 'FRIEND', '[{"type":"heading-two","children":[{"text":"학창시절부터 빛나던 당신의 재능"}]},{"type":"paragraph","children":[{"text":"지금도 변함없이 멋져요!","bold":true,"italic":true,"fontFamily":"Nanum Gothic","color":"#9400D3"}]}]', NULL, '2025-02-05'),
(7, '형', 'https://picsum.photos/id/522/100/100', 'beige', 'FAMILY', '[{"type":"paragraph","children":[{"text":"항상 꿈을 향해 달려가는 "},{"text":"동생이 정말 자랑스럽다","bold":true,"fontFamily":"sans-serif","color":"#A0522D"},{"text":". 형이 응원할게."}]}]', NULL, '2025-02-10'),
(8, '고객', NULL, 'green', 'OTHER', '[{"type":"heading-one","children":[{"text":"당신의 서비스는 항상 최고입니다."}]},{"type":"paragraph","children":[{"text":"앞으로도 잘 부탁드려요!","fontFamily":"Nanum Gothic","color":"#2E8B57"}]}]', NULL, '2025-02-15'),
(8, '이웃', 'https://picsum.photos/id/547/100/100', 'blue', 'OTHER', '[{"type":"paragraph","children":[{"text":"언제나 밝은 미소로 "},{"text":"인사해주셔서 감사합니다","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#1E90FF"},{"text":"."}]}]', NULL, '2025-02-20'),
(9, '친구', 'https://picsum.photos/id/268/100/100', 'purple', 'FRIEND', '[{"type":"heading-two","children":[{"text":"당신의 진심이 느껴져 감동입니다."}]},{"type":"paragraph","children":[{"text":"그 마음씨 변치 마세요.","fontFamily":"Nanum Gothic","color":"#9370DB"}]}]', NULL, '2025-02-25'),
(9, '동생', NULL, 'beige', 'FAMILY', '[{"type":"paragraph","children":[{"text":"누나가 항상 "},{"text":"응원하고 있어!","bold":true,"fontFamily":"sans-serif","color":"#B8860B"},{"text":" 화이팅!"}]}]', NULL, '2025-03-01'),
(10, '선생님', 'https://picsum.photos/id/1082/100/100', 'green', 'OTHER', '[{"type":"heading-one","children":[{"text":"너의 미래가 밝게 빛나길 바란다."}]},{"type":"paragraph","children":[{"text":"항상 응원하고 있단다.","fontFamily":"Nanum Gothic","color":"#228B22"}]}]', NULL, '2025-03-05'),
(10, '친구', 'https://picsum.photos/id/571/100/100', 'blue', 'FRIEND', '[{"type":"paragraph","children":[{"text":"함께한 추억들이 "},{"text":"내게는 보물같아","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#4682B4"},{"text":". 고마워!"}]}]', NULL, '2025-03-10'),
(11, '후배', NULL, 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"선배님의 지혜를 닮고 싶습니다."}]},{"type":"paragraph","children":[{"text":"항상 감사히 생각하고 있어요.","fontFamily":"Nanum Gothic","color":"#483D8B"}]}]', NULL, '2025-03-15'),
(11, '고객', 'https://picsum.photos/id/494/100/100', 'beige', 'OTHER', '[{"type":"paragraph","children":[{"text":"친절하고 "},{"text":"전문적인 서비스","bold":true,"fontFamily":"sans-serif","color":"#8B0000"},{"text":"에 항상 감사드립니다."}]}]', NULL, '2025-03-20'),
(12, '선배', 'https://picsum.photos/id/859/100/100', 'green', 'OTHER', '[{"type":"heading-one","children":[{"text":"네 성장이 자랑스럽구나."}]},{"type":"paragraph","children":[{"text":"앞으로도 꽃길만 걷기를!","fontFamily":"Nanum Gothic","color":"#556B2F"}]}]', NULL, '2025-03-25'),
(12, '동호회원', NULL, 'blue', 'OTHER', '[{"type":"paragraph","children":[{"text":"함께 취미를 즐길 수 있어 "},{"text":"항상 즐겁습니다","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#00688B"},{"text":"!"}]}]', NULL, '2025-03-30'),
(13, '멘토', 'https://picsum.photos/id/437/100/100', 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"당신의 성장이 기쁨입니다."}]},{"type":"paragraph","children":[{"text":"더 큰 세상으로 나아가세요!","bold":true,"fontFamily":"Nanum Gothic","color":"#9932CC"}]}]', NULL, '2025-01-02'),
(14, '친구', NULL, 'beige', 'FRIEND', '[{"type":"paragraph","children":[{"text":"너와의 "},{"text":"우정은 평생의 재산","bold":true,"fontFamily":"sans-serif","color":"#8B4513"},{"text":"이야. 항상 고마워!"}]}]', NULL, '2025-01-07'),
(15, '선배', 'https://picsum.photos/id/1064/100/100', 'green', 'OTHER', '[{"type":"heading-one","children":[{"text":"네 노력이 빛을 발하고 있어."}]},{"type":"paragraph","children":[{"text":"자랑스럽다!","fontFamily":"Nanum Gothic","color":"#2E8B57"}]}]', NULL, '2025-01-12'),
(16, '동료', 'https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png', 'blue', 'COLLEAGUE', '[{"type":"paragraph","children":[{"text":"당신의 전문성이 "},{"text":"우리 팀의 자산입니다","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#1E90FF"},{"text":"."}]}]', NULL, '2025-01-17'),
(17, '후배', NULL, 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"선배님의 조언이 제게는 등불입니다."}]},{"type":"paragraph","children":[{"text":"언제나 감사드립니다!","fontFamily":"Nanum Gothic","color":"#8A2BE2"}]}]', NULL, '2025-01-22'),
(18, '동창', 'https://picsum.photos/id/522/100/100', 'beige', 'FRIEND', '[{"type":"paragraph","children":[{"text":"20년이 지나도 "},{"text":"여전히 빛나는 사람","bold":true,"fontFamily":"sans-serif","color":"#CD853F"},{"text":"이구나!"}]}]', NULL, '2025-01-27'),
(19, '친구', 'https://picsum.photos/id/547/100/100', 'green', 'FRIEND', '[{"type":"heading-one","children":[{"text":"너의 도전을 항상 응원해!"}]},{"type":"paragraph","children":[{"text":"꿈을 향해 계속 나아가길.","fontFamily":"Nanum Gothic","color":"#006400"}]}]', NULL, '2025-01-30'),
(20, '가족', NULL, 'blue', 'FAMILY', '[{"type":"paragraph","children":[{"text":"우리 가족의 "},{"text":"든든한 버팀목","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#00008B"},{"text":"이야. 사랑해!"}]}]', NULL, '2025-02-03'),
(21, '고객', 'https://picsum.photos/id/268/100/100', 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"항상 최선을 다하는 모습이 감동입니다."}]},{"type":"paragraph","children":[{"text":"감사한 마음 전합니다!","fontFamily":"Nanum Gothic","color":"#9370DB"}]}]', NULL, '2025-02-08'),
(22, '동호회원', NULL, 'beige', 'OTHER', '[{"type":"paragraph","children":[{"text":"당신의 열정이 "},{"text":"우리 모임을 빛나게 합니다","bold":true,"fontFamily":"sans-serif","color":"#B8860B"},{"text":"."}]}]', NULL, '2025-02-10'),
(23, '이웃', 'https://picsum.photos/id/1082/100/100', 'green', 'OTHER', '[{"type":"heading-one","children":[{"text":"이웃이 당신같은 분이라 행복합니다."}]},{"type":"paragraph","children":[{"text":"앞으로도 좋은 이웃사촌 해요!","fontFamily":"Nanum Gothic","color":"#228B22"}]}]', NULL, '2025-02-15'),
(24, '멘티', 'https://picsum.photos/id/571/100/100', 'blue', 'OTHER', '[{"type":"paragraph","children":[{"text":"멘토님의 지도로 "},{"text":"제 인생이 달라졌습니다","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#4682B4"},{"text":". 감사합니다!"}]}]', NULL, '2025-02-20'),
(25, '친구', NULL, 'purple', 'FRIEND', '[{"type":"heading-two","children":[{"text":"네 곁에 있다는 것이 행복이야."}]},{"type":"paragraph","children":[{"text":"우정 영원하자!","fontFamily":"Nanum Gothic","color":"#8A2BE2"}]}]', NULL, '2025-02-25'),
(26, '제자', 'https://picsum.photos/id/494/100/100', 'beige', 'OTHER', '[{"type":"paragraph","children":[{"text":"선생님의 가르침이 "},{"text":"저의 인생 나침반","bold":true,"fontFamily":"sans-serif","color":"#8B4513"},{"text":"입니다."}]}]', NULL, '2025-03-01'),
(27, '동생', NULL, 'green', 'FAMILY', '[{"type":"heading-one","children":[{"text":"누나가 항상 응원하고 있어!"}]},{"type":"paragraph","children":[{"text":"자랑스러운 내 동생.","fontFamily":"Nanum Gothic","color":"#006400"}]}]', NULL, '2025-03-05'),
(28, '선배', 'https://picsum.photos/id/859/100/100', 'blue', 'OTHER', '[{"type":"paragraph","children":[{"text":"후배의 성장을 지켜보는 것이 "},{"text":"저에게는 큰 기쁨입니다","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#00008B"},{"text":"."}]}]', NULL, '2025-03-10'),
(29, '친구', 'https://picsum.photos/id/437/100/100', 'purple', 'FRIEND', '[{"type":"heading-two","children":[{"text":"너와 함께한 모든 순간이 빛납니다."}]},{"type":"paragraph","children":[{"text":"앞으로도 좋은 추억 만들자!","fontFamily":"Nanum Gothic","color":"#9932CC"}]}]', NULL, '2025-03-15'),
(30, '가족', NULL, 'beige', 'FAMILY', '[{"type":"paragraph","children":[{"text":"우리 가족 중 "},{"text":"가장 빛나는 별","bold":true,"fontFamily":"sans-serif","color":"#CD853F"},{"text":"이야. 사랑해!"}]}]', NULL, '2025-03-20');

-- 추가 메시지 (랜덤 생성 - 각 수신자에 더 많은 메시지 추가, 프로필 이미지 URL 추가 및 relationship 수정)
INSERT INTO messages (recipient_id, sender, profile_image_url, background_color, relationship, content, font, created_at) VALUES
(1, '선후배', 'https://picsum.photos/id/1064/100/100', 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"항상 본받고 싶은 선배님!"}]},{"type":"paragraph","children":[{"text":"멋진 모습에 감사드립니다.","fontFamily":"Nanum Gothic","color":"#800080"}]}]', NULL, '2025-01-02'),
(2, '대학동기', 'https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png', 'green', 'FRIEND', '[{"type":"paragraph","children":[{"text":"대학 시절부터 "},{"text":"항상 빛나던 너의 재능","bold":true,"fontFamily":"sans-serif","color":"#006400"},{"text":"이 지금은 더 빛나네!"}]}]', NULL, '2025-01-07'),
(3, '직장동료', NULL, 'blue', 'COLLEAGUE', '[{"type":"heading-one","children":[{"text":"함께 일하는 것이 즐겁습니다."}]},{"type":"paragraph","children":[{"text":"당신의 열정이 우리를 이끕니다!","fontFamily":"Nanum Gothic","color":"#00008B"}]}]', NULL, '2025-01-12'),
(4, '친구', NULL, 'beige', 'FRIEND', '[{"type":"paragraph","children":[{"text":"언제나 "},{"text":"긍정적인 에너지","bold":true,"italic":true,"fontFamily":"sans-serif","color":"#8B4513"},{"text":"를 주는 너에게 고마워!"}]}]', NULL, '2025-01-17'),
(5, '학생', NULL, 'purple', 'OTHER', '[{"type":"heading-two","children":[{"text":"선생님의 가르침이 제 삶을 바꿨어요."}]},{"type":"paragraph","children":[{"text":"언제나 감사한 마음입니다.","fontFamily":"Nanum Gothic","color":"#800080"}]}]', NULL, '2025-01-22');

-- Recipient 1 반응 (8개)
INSERT INTO reactions (recipient_id, emoji, count) VALUES
(1, ':thumbsup:', 15),
(1, ':heart:', 12),
(1, ':clap:', 18),
(1, ':tada:', 11),
(1, ':pray:', 14),
(1, ':love:', 22),
(1, ':fire:', 9),
(1, ':star:', 19);

-- Recipient 2 반응 (8개)
INSERT INTO reactions (recipient_id, emoji, count) VALUES
(2, ':thumbsup:', 17),
(2, ':heart:', 19),
(2, ':clap:', 13),
(2, ':tada:', 10),
(2, ':pray:', 16),
(2, ':love:', 25),
(2, ':star:', 15),
(2, ':rocket:', 8);

-- Recipient 3 반응 (8개)
INSERT INTO reactions (recipient_id, emoji, count) VALUES
(3, ':thumbsup:', 12),
(3, ':heart:', 14),
(3, ':clap:', 9),
(3, ':tada:', 7),
(3, ':love:', 20),
(3, ':star:', 13),
(3, ':fire:', 18),
(3, ':100:', 11);

-- Recipient 4 반응 (8개)
INSERT INTO reactions (recipient_id, emoji, count) VALUES
(4, ':thumbsup:', 13),
(4, ':heart:', 18),
(4, ':clap:', 11),
(4, ':tada:', 8),
(4, ':love:', 15),
(4, ':fire:', 10),
(4, ':rocket:', 9),
(4, ':sparkles:', 14);

-- Recipient 5 반응 (8개)
INSERT INTO reactions (recipient_id, emoji, count) VALUES
(5, ':thumbsup:', 16),
(5, ':heart:', 13),
(5, ':clap:', 10),
(5, ':tada:', 9),
(5, ':love:', 18),
(5, ':star:', 12),
(5, ':100:', 7),
(5, ':gift:', 15);

-- Recipient 6-30 반응 (각각 8개)
INSERT INTO reactions (recipient_id, emoji, count) VALUES
(6, ':thumbsup:', 14),
(6, ':heart:', 20),
(6, ':clap:', 12),
(6, ':tada:', 10),
(6, ':love:', 17),
(6, ':star:', 8),
(6, ':rainbow:', 11),
(6, ':laughing:', 13),

(7, ':thumbsup:', 19),
(7, ':heart:', 15),
(7, ':clap:', 8),
(7, ':tada:', 12),
(7, ':pray:', 10),
(7, ':fire:', 14),
(7, ':100:', 9),
(7, ':gift:', 16),

(8, ':thumbsup:', 11),
(8, ':heart:', 17),
(8, ':clap:', 14),
(8, ':tada:', 9),
(8, ':love:', 13),
(8, ':rocket:', 7),
(8, ':sparkles:', 18), 
(9, ':heart:', 12),
(9, ':clap:', 15),
(9, ':tada:', 11),
(9, ':love:', 14),
(9, ':star:', 10),
(9, ':fire:', 13),
(9, ':100:', 8),

(10, ':thumbsup:', 20),
(10, ':heart:', 14),
(10, ':clap:', 12),
(10, ':tada:', 9),
(10, ':love:', 17),
(10, ':star:', 8),
(10, ':rainbow:', 11),
(10, ':laughing:', 13),

(10, ':thumbsup:', 20),
(10, ':heart:', 14),
(10, ':clap:', 12),
(10, ':tada:', 9),
(10, ':love:', 17),
(10, ':star:', 8),
(10, ':rainbow:', 11),
(10, ':laughing:', 13);

-- ====================================================================
-- BLOB 도메인 초기화 데이터
-- ====================================================================

-- BLOB 사용자 데이터 삭제 및 생성
DELETE FROM blob_likes;
DELETE FROM blob_bookmarks;
DELETE FROM blob_notifications;
DELETE FROM blob_reports;
DELETE FROM blob_comments;
DELETE FROM blob_post_images;
DELETE FROM blob_posts;
DELETE FROM blob_users;

-- BLOB 사용자 10명 생성
INSERT INTO blob_users (blob_id, nickname, email, profile_image_url, provider, provider_id, bio, state, is_public, coordinate_lat, coordinate_lng, oauth_type, role, post_count, liked_count, comment_count, created_at, updated_at) VALUES
('traveler_jane', '여행하는 제인', 'jane@example.com', 'https://picsum.photos/id/1011/100/100', 'google', 'google_123456', '세계를 여행하며 맛집과 명소를 공유합니다 ✈️', 'COMPLETE', true, 37.5665, 126.9780, 'GOOGLE', 'ROLE_USER', 12, 45, 38, '2024-01-15 10:30:00', '2024-01-15 10:30:00'),
('foodie_kim', '맛집탐험가김', 'kim.foodie@naver.com', 'https://picsum.photos/id/1027/100/100', 'naver', 'naver_789012', '전국 맛집을 찾아다니는 푸디입니다 🍴', 'COMPLETE', true, 35.1796, 129.0756, 'NAVER', 'ROLE_USER', 8, 23, 19, '2024-01-20 14:20:00', '2024-01-20 14:20:00'),
('seoul_walker', '서울산책러', 'walker.seoul@kakao.com', 'https://picsum.photos/id/1074/100/100', 'kakao', 'kakao_345678', '서울 곳곳의 숨은 명소를 발견하고 공유해요 👣', 'COMPLETE', true, 37.5665, 126.9780, 'KAKAO', 'ROLE_USER', 15, 67, 42, '2024-02-01 09:15:00', '2024-02-01 09:15:00'),
('busan_lover', '부산사랑', 'busan.love@gmail.com', 'https://picsum.photos/id/1062/100/100', 'google', 'google_901234', '부산 바다와 함께하는 일상을 기록합니다 🌊', 'COMPLETE', true, 35.1796, 129.0756, 'GOOGLE', 'ROLE_USER', 6, 18, 12, '2024-02-10 16:45:00', '2024-02-10 16:45:00'),
('mountain_hiker', '등산매니아', 'hiker.mountain@naver.com', 'https://picsum.photos/id/1080/100/100', 'naver', 'naver_567890', '전국 명산을 정복하는 등산러입니다 ⛰️', 'COMPLETE', true, 36.3504, 127.3845, 'NAVER', 'ROLE_USER', 9, 31, 24, '2024-02-15 08:30:00', '2024-02-15 08:30:00'),
('cafe_curator', '카페큐레이터', 'cafe.curator@kakao.com', 'https://picsum.photos/id/1058/100/100', 'kakao', 'kakao_234567', '감성 카페와 디저트를 큐레이팅합니다 ☕', 'COMPLETE', true, 37.5665, 126.9780, 'KAKAO', 'ROLE_USER', 11, 39, 28, '2024-02-20 13:20:00', '2024-02-20 13:20:00'),
('culture_hunter', '문화사냥꾼', 'culture.hunter@gmail.com', 'https://picsum.photos/id/1069/100/100', 'google', 'google_678901', '박물관, 갤러리, 공연 정보를 나눕니다 🎭', 'COMPLETE', true, 37.5665, 126.9780, 'GOOGLE', 'ROLE_USER', 7, 22, 16, '2024-02-25 11:10:00', '2024-02-25 11:10:00'),
('local_guide', '동네가이드', 'local.guide@naver.com', 'https://picsum.photos/id/1084/100/100', 'naver', 'naver_890123', '우리 동네 숨은 보석들을 소개합니다 💎', 'COMPLETE', true, 37.4563, 126.7052, 'NAVER', 'ROLE_USER', 13, 48, 35, '2024-03-01 15:40:00', '2024-03-01 15:40:00'),
('photo_artist', '사진작가', 'photo.artist@kakao.com', 'https://picsum.photos/id/1025/100/100', 'kakao', 'kakao_012345', '렌즈로 담은 아름다운 순간들을 공유해요 📸', 'COMPLETE', true, 35.8714, 128.6014, 'KAKAO', 'ROLE_USER', 18, 76, 54, '2024-03-05 12:25:00', '2024-03-05 12:25:00'),
('student_explorer', '대학생탐험가', 'student.explorer@gmail.com', 'https://picsum.photos/id/1044/100/100', 'google', 'google_456789', '학생 예산으로 즐기는 알찬 여행 팁 🎓', 'COMPLETE', true, 37.5665, 126.9780, 'GOOGLE', 'ROLE_USER', 5, 15, 21, '2024-03-10 17:50:00', '2024-03-10 17:50:00');

-- BLOB 게시물 생성 (각 사용자별 다양한 카테고리)
INSERT INTO blob_posts (user_id, title, content, category, subcategory, country, city, city_lat, city_lng, latitude, longitude, address, actual_lat, actual_lng, dist_from_actual, like_count, comment_count, view_count, created_at, updated_at, expires_at) VALUES
-- traveler_jane의 게시물 (3개)
(1, '제주도 숨은 맛집 발견!', '우도에서 발견한 정말 맛있는 해산물 요리집이에요. 현지인들만 아는 곳이라더니 정말 맛있었어요!', 'RECOMMENDED', 'RESTAURANT', '대한민국', '제주시', 33.4996, 126.5312, 33.5037, 126.9521, '제주특별자치도 제주시 우도면 연평리', 33.5040, 126.9525, 35.2, 8, 3, 127, '2024-03-15 11:20:00', '2024-03-15 11:20:00', '2024-06-15 11:20:00'),
(1, '부산 감천문화마을 추천', '부산의 산토리니라고 불리는 감천문화마을! 사진 찍기 좋은 포토존이 곳곳에 있어요', 'RECOMMENDED', 'ATTRACTIONS', '대한민국', '부산광역시', 35.1796, 129.0756, 35.0979, 129.0108, '부산광역시 사하구 감천2동', 35.0982, 129.0110, 28.1, 15, 7, 203, '2024-03-18 14:30:00', '2024-03-18 14:30:00', '2024-06-18 14:30:00'),
(1, '경주 한옥스테이 후기', '경주에서 전통 한옥에서 하룻밤 지냈는데 정말 특별한 경험이었어요. 조용하고 평화로운 밤을 보낼 수 있었습니다', 'RECOMMENDED', 'ACCOMMODATION', '대한민국', '경주시', 35.8562, 129.2247, 35.8419, 129.2083, '경상북도 경주시 황남동', 35.8422, 129.2086, 41.3, 12, 5, 156, '2024-03-20 09:45:00', '2024-03-20 09:45:00', '2024-06-20 09:45:00'),

-- foodie_kim의 게시물 (2개)  
(2, '홍대 신상 파스타집 주의!', '홍대에 새로 생긴 파스타집 갔는데 양도 적고 맛도 별로예요. 가격은 비싸고... 추천하지 않아요', 'NOT_RECOMMENDED', 'RESTAURANT', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5563, 126.9238, '서울특별시 마포구 홍익로 3길', 37.5566, 126.9241, 32.8, 6, 4, 89, '2024-03-22 18:20:00', '2024-03-22 18:20:00', '2024-06-22 18:20:00'),
(2, '강남 브런치 맛집 추천!', '강남역 근처에 있는 브런치 카페인데 분위기도 좋고 음식도 맛있어요! 특히 에그베네딕트가 일품입니다', 'RECOMMENDED', 'RESTAURANT', '대한민국', '서울특별시', 37.5665, 126.9780, 37.4979, 127.0276, '서울특별시 강남구 강남대로 지하', 37.4982, 127.0279, 35.1, 18, 8, 234, '2024-03-25 12:15:00', '2024-03-25 12:15:00', '2024-06-25 12:15:00'),

-- seoul_walker의 게시물 (4개)
(3, '한강 야경 포인트', '반포대교에서 보는 한강 야경이 정말 아름다워요. 데이트 코스로 강추합니다!', 'RECOMMENDED', 'ATTRACTIONS', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5133, 127.0064, '서울특별시 서초구 반포동', 37.5136, 127.0067, 38.4, 23, 12, 145, '2024-03-28 20:30:00', '2024-03-28 20:30:00', '2024-06-28 20:30:00'),
(3, '명동 화장실 정보', '명동 쇼핑하다가 급할 때 쓸 수 있는 깨끗한 화장실 위치 공유해요', 'HELP', 'TOILET', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5636, 126.9834, '서울특별시 중구 명동2가', 37.5639, 126.9837, 41.2, 9, 3, 78, '2024-03-30 15:40:00', '2024-03-30 15:40:00', '2024-06-30 15:40:00'),
(3, '건대 24시간 약국', '건대입구에서 밤늦게 약국 찾으시는 분들을 위해 24시간 운영하는 약국 정보 남겨요', 'HELP', 'PHARMACY', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5403, 127.0694, '서울특별시 광진구 화양동', 37.5406, 127.0697, 33.7, 14, 6, 91, '2024-04-02 22:10:00', '2024-04-02 22:10:00', '2024-07-02 22:10:00'),
(3, '이태원 교통 주의!', '이태원 일대 도로공사로 인해 교통이 매우 혼잡해요. 대중교통 이용 추천합니다', 'WARNING', 'TRANSPORT', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5346, 126.9947, '서울특별시 용산구 이태원동', 37.5349, 126.9950, 36.9, 11, 8, 134, '2024-04-05 16:25:00', '2024-04-05 16:25:00', '2024-07-05 16:25:00'),

-- busan_lover의 게시물 (2개)
(4, '해운대 해수욕장 날씨 정보', '오늘 해운대 날씨가 정말 좋아요! 파도도 잔잔하고 해수욕하기 딱 좋은 날씨입니다', 'RECOMMENDED', 'WEATHER', '대한민국', '부산광역시', 35.1796, 129.0756, 35.1588, 129.1603, '부산광역시 해운대구 우동', 35.1591, 129.1606, 28.3, 19, 5, 167, '2024-04-08 10:30:00', '2024-04-08 10:30:00', '2024-07-08 10:30:00'),
(4, '광안리 병원 추천', '광안리 근처에서 응급상황 발생했을 때 갈 수 있는 좋은 병원 정보 공유합니다', 'HELP', 'HOSPITAL', '대한민국', '부산광역시', 35.1796, 129.0756, 35.1537, 129.1186, '부산광역시 수영구 광안동', 35.1540, 129.1189, 31.5, 7, 2, 56, '2024-04-10 14:20:00', '2024-04-10 14:20:00', '2024-07-10 14:20:00'),

-- mountain_hiker의 게시물 (2개)
(5, '설악산 등반 경로 추천', '설악산 대청봉 등반하고 왔어요! 초보자도 할 수 있는 경로와 팁 공유합니다', 'RECOMMENDED', 'ATTRACTIONS', '대한민국', '속초시', 38.2070, 128.5918, 38.1197, 128.4653, '강원특별자치도 속초시 설악동', 38.1200, 128.4656, 42.1, 22, 9, 201, '2024-04-12 07:00:00', '2024-04-12 07:00:00', '2024-07-12 07:00:00'),
(5, '지리산 날씨 주의보', '지리산 일대에 갑작스런 안개와 비 예보가 있어요. 등산 계획 있으신 분들은 주의하세요!', 'WARNING', 'WEATHER', '대한민국', '남원시', 35.4164, 127.3906, 35.3350, 127.7303, '전라북도 남원시 산내면', 35.3353, 127.7306, 38.7, 16, 7, 89, '2024-04-15 06:30:00', '2024-04-15 06:30:00', '2024-07-15 06:30:00'),

-- cafe_curator의 게시물 (3개)
(6, '성수동 감성 카페', '성수동에 새로 생긴 인더스트리얼 컨셉 카페예요. 분위기도 좋고 커피도 맛있어요!', 'RECOMMENDED', 'RESTAURANT', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5447, 127.0557, '서울특별시 성동구 성수동1가', 37.5450, 127.0560, 34.6, 25, 11, 189, '2024-04-18 13:45:00', '2024-04-18 13:45:00', '2024-07-18 13:45:00'),
(6, '압구정 디저트 카페 실망', '압구정 로데오거리 디저트 카페 갔는데 가격 대비 맛이 별로였어요. 인스타용으로는 예쁘지만...', 'NOT_RECOMMENDED', 'RESTAURANT', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5276, 127.0384, '서울특별시 강남구 신사동', 37.5279, 127.0387, 29.8, 8, 6, 102, '2024-04-20 16:20:00', '2024-04-20 16:20:00', '2024-07-20 16:20:00'),
(6, '홍대 ATM 위치 정보', '홍대에서 급하게 현금 필요할 때 쓸 수 있는 ATM 위치들 정리해서 올려요', 'HELP', 'ATM', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5563, 126.9238, '서울특별시 마포구 홍익로', 37.5566, 126.9241, 32.4, 12, 4, 67, '2024-04-22 19:30:00', '2024-04-22 19:30:00', '2024-07-22 19:30:00'),

-- culture_hunter의 게시물 (2개)
(7, '국립중앙박물관 특별전', '국립중앙박물관에서 하고 있는 특별전이 정말 볼 만해요! 한국사에 관심 있으신 분들께 추천', 'RECOMMENDED', 'MUSEUM', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5240, 126.9803, '서울특별시 용산구 서빙고로 137', 37.5243, 126.9806, 34.1, 18, 8, 145, '2024-04-25 11:00:00', '2024-04-25 11:00:00', '2024-07-25 11:00:00'),
(7, '대학로 공연 정보 질문', '대학로에서 뮤지컬 보려고 하는데 주차할 곳이 있을까요? 대중교통이 나을까요?', 'QUESTION', 'TRANSPORT', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5820, 127.0006, '서울특별시 종로구 대학로', 37.5823, 127.0009, 33.2, 6, 9, 78, '2024-04-28 17:40:00', '2024-04-28 17:40:00', '2024-07-28 17:40:00'),

-- local_guide의 게시물 (3개)
(8, '인천 차이나타운 맛집', '인천 차이나타운에서 짜장면 맛집 발견했어요! 현지인이 추천해준 곳이라 더 믿음직스러워요', 'RECOMMENDED', 'RESTAURANT', '대한민국', '인천광역시', 37.4563, 126.7052, 37.4760, 126.6173, '인천광역시 중구 차이나타운로', 37.4763, 126.6176, 35.7, 21, 7, 176, '2024-05-01 12:30:00', '2024-05-01 12:30:00', '2024-08-01 12:30:00'),
(8, '인천공항 교통편 정보', '인천공항에서 시내로 가는 가장 저렴하고 빠른 교통편 정보 정리해서 올려요', 'HELP', 'TRANSPORT', '대한민국', '인천광역시', 37.4563, 126.7052, 37.4691, 126.4505, '인천광역시 중구 운서동', 37.4694, 126.4508, 41.2, 28, 12, 298, '2024-05-03 08:15:00', '2024-05-03 08:15:00', '2024-08-03 08:15:00'),
(8, '송도 숙박 추천', '송도에서 하룻밤 묵을 만한 깨끗하고 가성비 좋은 숙소 추천드려요!', 'RECOMMENDED', 'ACCOMMODATION', '대한민국', '인천광역시', 37.4563, 126.7052, 37.3894, 126.6417, '인천광역시 연수구 송도동', 37.3897, 126.6420, 29.6, 14, 5, 132, '2024-05-05 20:45:00', '2024-05-05 20:45:00', '2024-08-05 20:45:00'),

-- photo_artist의 게시물 (4개)
(9, '남산타워 사진 촬영 팁', '남산타워에서 야경 사진 찍는 베스트 포인트와 카메라 설정 팁 공유해요!', 'RECOMMENDED', 'ATTRACTIONS', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5512, 126.9882, '서울특별시 용산구 용산동2가', 37.5515, 126.9885, 38.9, 31, 15, 267, '2024-05-08 19:20:00', '2024-05-08 19:20:00', '2024-08-08 19:20:00'),
(9, '경복궁 포토존', '경복궁에서 한복 입고 사진 찍기 좋은 포토존들 정리해서 올려요. 조명과 각도 팁도 함께!', 'RECOMMENDED', 'ATTRACTIONS', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5796, 126.9770, '서울특별시 종로구 세종로', 37.5799, 126.9773, 31.4, 26, 13, 201, '2024-05-10 14:30:00', '2024-05-10 14:30:00', '2024-08-10 14:30:00'),
(9, '동대문 야경 촬영', '동대문 디자인 플라자 야경 촬영하고 왔어요. LED 조명이 정말 아름다워요!', 'RECOMMENDED', 'ATTRACTIONS', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5669, 127.0092, '서울특별시 중구 을지로7가', 37.5672, 127.0095, 32.8, 19, 8, 154, '2024-05-12 21:00:00', '2024-05-12 21:00:00', '2024-08-12 21:00:00'),
(9, '광화문 날씨 주의', '광화문 광장 일대에 돌풍이 불고 있어요. 사진 촬영 계획 있으신 분들은 주의하세요!', 'WARNING', 'WEATHER', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5720, 126.9769, '서울특별시 종로구 세종로', 37.5723, 126.9772, 29.1, 8, 4, 67, '2024-05-15 10:45:00', '2024-05-15 10:45:00', '2024-08-15 10:45:00'),

-- student_explorer의 게시물 (1개)
(10, '대학생 여행 꿀팁', '적은 예산으로도 알차게 여행하는 방법들 정리해서 공유해요! 숙박비 절약 팁부터 맛집까지', 'HELP', 'ACCOMMODATION', '대한민국', '서울특별시', 37.5665, 126.9780, 37.5490, 127.0761, '서울특별시 광진구 자양동', 37.5493, 127.0764, 35.3, 24, 18, 312, '2024-05-18 15:20:00', '2024-05-18 15:20:00', '2024-08-18 15:20:00');

-- BLOB 게시물 이미지 생성
INSERT INTO blob_post_images (post_id, image_url, image_order) VALUES
-- traveler_jane의 게시물 이미지
(1, 'https://picsum.photos/id/1080/800/600', 1),
(1, 'https://picsum.photos/id/1081/800/600', 2),
(2, 'https://picsum.photos/id/1082/800/600', 1),
(3, 'https://picsum.photos/id/1083/800/600', 1),
(3, 'https://picsum.photos/id/1084/800/600', 2),
(3, 'https://picsum.photos/id/1085/800/600', 3),

-- foodie_kim의 게시물 이미지
(5, 'https://picsum.photos/id/1086/800/600', 1),
(5, 'https://picsum.photos/id/1087/800/600', 2),

-- seoul_walker의 게시물 이미지
(6, 'https://picsum.photos/id/1088/800/600', 1),

-- cafe_curator의 게시물 이미지
(11, 'https://picsum.photos/id/1089/800/600', 1),
(11, 'https://picsum.photos/id/1090/800/600', 2),
(13, 'https://picsum.photos/id/1091/800/600', 1),

-- culture_hunter의 게시물 이미지
(14, 'https://picsum.photos/id/1092/800/600', 1),

-- local_guide의 게시물 이미지
(16, 'https://picsum.photos/id/1093/800/600', 1),
(16, 'https://picsum.photos/id/1094/800/600', 2),

-- photo_artist의 게시물 이미지
(19, 'https://picsum.photos/id/1095/800/600', 1),
(19, 'https://picsum.photos/id/1096/800/600', 2),
(19, 'https://picsum.photos/id/1097/800/600', 3),
(20, 'https://picsum.photos/id/1098/800/600', 1),
(20, 'https://picsum.photos/id/1099/800/600', 2),
(21, 'https://picsum.photos/id/1100/800/600', 1),
(21, 'https://picsum.photos/id/1101/800/600', 2),

-- student_explorer의 게시물 이미지
(23, 'https://picsum.photos/id/1102/800/600', 1);

-- BLOB 댓글 생성
INSERT INTO blob_comments (post_id, user_id, content, like_count, created_at, updated_at) VALUES
-- 제주도 맛집 게시물에 대한 댓글들
(1, 2, '우도 정말 좋죠! 저도 다음에 가봐야겠어요 😊', 3, '2024-03-15 12:30:00', '2024-03-15 12:30:00'),
(1, 3, '해산물 요리 정말 맛있을 것 같아요. 정확한 위치 알 수 있을까요?', 2, '2024-03-15 14:20:00', '2024-03-15 14:20:00'),
(1, 5, '현지인 맛집이 진짜 맛있죠! 좋은 정보 감사해요', 1, '2024-03-16 09:15:00', '2024-03-16 09:15:00'),

-- 부산 감천문화마을에 대한 댓글들
(2, 4, '감천문화마을은 정말 예뻐요! 사진도 잘 나오고', 5, '2024-03-18 16:45:00', '2024-03-18 16:45:00'),
(2, 6, '부산 갈 때마다 들르는 곳이에요. 야경도 예뻐요', 3, '2024-03-19 10:20:00', '2024-03-19 10:20:00'),
(2, 8, '관광버스 타고 가면 편해요!', 2, '2024-03-19 15:30:00', '2024-03-19 15:30:00'),
(2, 9, '포토존 정보도 공유해주세요!', 1, '2024-03-20 11:10:00', '2024-03-20 11:10:00'),
(2, 10, '학생할인 있나요?', 0, '2024-03-20 17:25:00', '2024-03-20 17:25:00'),

-- 경주 한옥스테이에 대한 댓글들
(3, 7, '한옥에서 자는 경험 정말 특별하겠어요', 2, '2024-03-20 11:30:00', '2024-03-20 11:30:00'),
(3, 8, '예약은 어떻게 하나요?', 1, '2024-03-20 13:45:00', '2024-03-20 13:45:00'),
(3, 2, '가격이 어느 정도인지 궁금해요', 1, '2024-03-21 08:20:00', '2024-03-21 08:20:00'),

-- 홍대 파스타집 주의에 대한 댓글들
(4, 1, '아 실망이시겠어요 ㅠㅠ 정보 고마워요', 4, '2024-03-22 19:40:00', '2024-03-22 19:40:00'),
(4, 3, '홍대에 맛있는 파스타집 추천해드릴게요!', 3, '2024-03-23 12:15:00', '2024-03-23 12:15:00'),
(4, 6, '요즘 홍대 맛집 찾기 힘들어요ㅠ', 2, '2024-03-23 18:30:00', '2024-03-23 18:30:00'),

-- 강남 브런치 맛집에 대한 댓글들
(5, 6, '에그베네딕트 정말 맛있어 보여요! 꼭 가봐야겠어요', 6, '2024-03-25 13:45:00', '2024-03-25 13:45:00'),
(5, 7, '브런치 맛집 정보 감사해요 ☕', 4, '2024-03-25 16:20:00', '2024-03-25 16:20:00'),
(5, 9, '사진 찍기도 좋은 곳인가요?', 2, '2024-03-26 10:30:00', '2024-03-26 10:30:00'),
(5, 10, '가격대는 어느 정도인가요?', 1, '2024-03-26 14:15:00', '2024-03-26 14:15:00'),

-- 한강 야경에 대한 댓글들
(6, 1, '반포대교 야경 정말 로맨틱해요 💕', 8, '2024-03-28 21:45:00', '2024-03-28 21:45:00'),
(6, 2, '데이트 코스로 완전 추천이에요!', 5, '2024-03-29 09:20:00', '2024-03-29 09:20:00'),
(6, 4, '야경 보면서 치킨 먹는 것도 좋아요 ㅎㅎ', 3, '2024-03-29 20:10:00', '2024-03-29 20:10:00'),

-- 설악산 등반에 대한 댓글들
(10, 3, '등산 초보인데 정말 도움 되는 정보네요!', 7, '2024-04-12 08:30:00', '2024-04-12 08:30:00'),
(10, 6, '설악산 단풍 시즌에 가면 더 예뻐요', 5, '2024-04-12 15:20:00', '2024-04-12 15:20:00'),
(10, 8, '등반 시간은 얼마나 걸리나요?', 2, '2024-04-13 07:45:00', '2024-04-13 07:45:00'),

-- 성수동 카페에 대한 댓글들
(11, 2, '성수동 카페 진짜 감성 있어요! 가봐야겠어요', 9, '2024-04-18 15:30:00', '2024-04-18 15:30:00'),
(11, 7, '인더스트리얼 컨셉 좋아해요 ☕', 6, '2024-04-18 18:45:00', '2024-04-18 18:45:00'),
(11, 9, '사진 찍기 좋은 스팟 있나요?', 3, '2024-04-19 11:20:00', '2024-04-19 11:20:00'),
(11, 10, '학생할인 있는지 궁금해요', 1, '2024-04-19 16:40:00', '2024-04-19 16:40:00'),

-- 남산타워 사진 촬영에 대한 댓글들
(19, 1, '사진 팁 정말 유용해요! 다음에 가서 써봐야겠어요', 12, '2024-05-08 20:45:00', '2024-05-08 20:45:00'),
(19, 3, '야경 촬영 정말 어려운데 팁 감사해요', 8, '2024-05-09 10:30:00', '2024-05-09 10:30:00'),
(19, 6, '카메라 설정값도 더 자세히 알려주세요!', 5, '2024-05-09 14:20:00', '2024-05-09 14:20:00'),
(19, 7, '남산타워 야경은 언제 봐도 아름다워요', 4, '2024-05-09 18:15:00', '2024-05-09 18:15:00'),

-- 대학생 여행 꿀팁에 대한 댓글들
(23, 1, '대학생 때 이런 정보 있었으면 좋았을 텐데 ㅠㅠ', 8, '2024-05-18 16:45:00', '2024-05-18 16:45:00'),
(23, 2, '예산 관리 팁 정말 실용적이에요!', 6, '2024-05-18 19:30:00', '2024-05-18 19:30:00'),
(23, 3, '배낭여행 갈 때 참고하겠습니다', 4, '2024-05-19 09:15:00', '2024-05-19 09:15:00'),
(23, 5, '등산 여행할 때도 적용할 수 있겠네요', 3, '2024-05-19 12:40:00', '2024-05-19 12:40:00'),
(23, 6, '카페 투어할 때도 써봐야겠어요', 2, '2024-05-19 15:20:00', '2024-05-19 15:20:00');

-- BLOB 대댓글 생성 (parent_comment_id 설정)
INSERT INTO blob_comments (post_id, user_id, parent_comment_id, content, like_count, created_at, updated_at) VALUES
-- 제주도 맛집 댓글의 대댓글
(1, 1, 2, '가게 이름은 "바다향"이에요! 우도 선착장에서 도보 5분 거리예요', 5, '2024-03-15 16:30:00', '2024-03-15 16:30:00'),
(1, 3, 2, '감사해요! 꼭 가보겠습니다 ☺️', 2, '2024-03-15 18:45:00', '2024-03-15 18:45:00'),

-- 부산 감천문화마을 댓글의 대댓글
(2, 2, 4, '맞아요! 특히 해질 무렵 야경이 환상적이에요', 3, '2024-03-18 17:20:00', '2024-03-18 17:20:00'),
(2, 2, 7, '마을 곳곳에 포토존이 있어요. 특히 하늘계단이 유명해요!', 4, '2024-03-20 12:30:00', '2024-03-20 12:30:00'),
(2, 2, 8, '학생증 제시하면 입장료 할인 받을 수 있어요', 1, '2024-03-20 18:40:00', '2024-03-20 18:40:00'),

-- 경주 한옥스테이 댓글의 대댓글
(3, 1, 10, '네이버나 여기어때에서 예약 가능해요!', 2, '2024-03-20 15:20:00', '2024-03-20 15:20:00'),
(3, 1, 11, '1박에 8만원 정도였어요. 조식 포함이에요', 3, '2024-03-21 10:45:00', '2024-03-21 10:45:00'),

-- 홍대 파스타집 댓글의 대댓글
(4, 2, 13, '오 어디인지 알려주세요!', 2, '2024-03-23 13:30:00', '2024-03-23 13:30:00'),
(4, 3, 13, '홍대입구역 근처 "파스타 디 마마" 추천해요!', 4, '2024-03-23 14:15:00', '2024-03-23 14:15:00'),

-- 강남 브런치 맛집 댓글의 대댓글
(5, 2, 15, '네! 인테리어도 예쁘고 인스타 감성이에요 📸', 3, '2024-03-25 17:40:00', '2024-03-25 17:40:00'),
(5, 2, 16, '브런치 세트가 2만원대예요. 가성비 좋아요!', 2, '2024-03-26 11:20:00', '2024-03-26 11:20:00'),

-- 한강 야경 댓글의 대댓글
(6, 3, 17, '맞아요! 분위기 완전 로맨틱해요 ✨', 4, '2024-03-29 10:30:00', '2024-03-29 10:30:00'),
(6, 3, 19, '치킨에 맥주 조합 최고죠! ㅋㅋ', 2, '2024-03-29 21:25:00', '2024-03-29 21:25:00'),

-- 설악산 등반 댓글의 대댓글
(10, 5, 20, '천천히 가시면 4-5시간 정도 걸려요. 충분히 가능해요!', 5, '2024-04-12 09:45:00', '2024-04-12 09:45:00'),
(10, 5, 22, '왕복 8시간 정도 보시면 여유롭게 다녀올 수 있어요', 3, '2024-04-13 08:30:00', '2024-04-13 08:30:00'),

-- 성수동 카페 댓글의 대댓글
(11, 6, 25, '네! 창가 자리에서 사진 찍으면 예뻐요', 4, '2024-04-19 12:45:00', '2024-04-19 12:45:00'),
(11, 6, 26, '학생할인은 10% 해주더라고요!', 2, '2024-04-19 17:20:00', '2024-04-19 17:20:00'),

-- 남산타워 사진 촬영 댓글의 대댓글
(19, 9, 29, 'ISO 800, 조리개 f/8, 셔터 1/60초 정도로 설정해보세요!', 7, '2024-05-09 15:40:00', '2024-05-09 15:40:00'),
(19, 9, 30, '맞아요! 서울의 야경은 정말 아름다워요 🌃', 3, '2024-05-09 19:30:00', '2024-05-09 19:30:00'),

-- 대학생 여행 꿀팁 댓글의 대댓글
(23, 10, 32, '네! 저도 예산 관리 하면서 여행 다니고 있어요', 4, '2024-05-18 20:15:00', '2024-05-18 20:15:00'),
(23, 10, 34, '등산할 때도 숙박비 아끼는 팁 많이 써먹어요!', 2, '2024-05-19 13:25:00', '2024-05-19 13:25:00'),
(23, 10, 35, '카페 투어할 때는 할인쿠폰 미리 찾아보세요!', 3, '2024-05-19 16:10:00', '2024-05-19 16:10:00');

-- BLOB 좋아요 데이터 생성
INSERT INTO blob_likes (user_id, target_type, target_id, created_at) VALUES
-- traveler_jane의 게시물에 대한 좋아요
(2, 'POST', 1, '2024-03-15 13:00:00'), (3, 'POST', 1, '2024-03-15 15:30:00'), (4, 'POST', 1, '2024-03-16 10:20:00'), 
(5, 'POST', 1, '2024-03-16 14:45:00'), (6, 'POST', 1, '2024-03-17 09:15:00'), (7, 'POST', 1, '2024-03-17 16:30:00'),
(8, 'POST', 1, '2024-03-18 11:40:00'), (9, 'POST', 1, '2024-03-18 18:20:00'),

(1, 'POST', 2, '2024-03-18 15:00:00'), (3, 'POST', 2, '2024-03-18 17:30:00'), (4, 'POST', 2, '2024-03-19 08:45:00'),
(5, 'POST', 2, '2024-03-19 12:20:00'), (6, 'POST', 2, '2024-03-19 16:10:00'), (7, 'POST', 2, '2024-03-20 09:30:00'),
(8, 'POST', 2, '2024-03-20 13:15:00'), (9, 'POST', 2, '2024-03-20 17:45:00'), (10, 'POST', 2, '2024-03-21 10:25:00'),

(2, 'POST', 3, '2024-03-20 12:00:00'), (3, 'POST', 3, '2024-03-20 15:20:00'), (4, 'POST', 3, '2024-03-21 09:40:00'),
(5, 'POST', 3, '2024-03-21 14:15:00'), (6, 'POST', 3, '2024-03-21 18:30:00'), (7, 'POST', 3, '2024-03-22 11:45:00'),

-- foodie_kim의 게시물에 대한 좋아요
(1, 'POST', 4, '2024-03-22 20:00:00'), (3, 'POST', 4, '2024-03-23 08:30:00'), (4, 'POST', 4, '2024-03-23 13:45:00'),
(5, 'POST', 4, '2024-03-23 17:20:00'), (7, 'POST', 4, '2024-03-24 10:15:00'), (8, 'POST', 4, '2024-03-24 15:40:00'),

(1, 'POST', 5, '2024-03-25 13:00:00'), (3, 'POST', 5, '2024-03-25 16:45:00'), (4, 'POST', 5, '2024-03-25 19:30:00'),
(5, 'POST', 5, '2024-03-26 09:20:00'), (6, 'POST', 5, '2024-03-26 12:15:00'), (7, 'POST', 5, '2024-03-26 15:40:00'),
(8, 'POST', 5, '2024-03-26 18:25:00'), (9, 'POST', 5, '2024-03-27 10:50:00'), (10, 'POST', 5, '2024-03-27 14:35:00'),

-- seoul_walker의 게시물에 대한 좋아요
(1, 'POST', 6, '2024-03-28 21:00:00'), (2, 'POST', 6, '2024-03-29 08:30:00'), (4, 'POST', 6, '2024-03-29 12:45:00'),
(5, 'POST', 6, '2024-03-29 16:20:00'), (6, 'POST', 6, '2024-03-29 19:40:00'), (7, 'POST', 6, '2024-03-30 10:15:00'),
(8, 'POST', 6, '2024-03-30 14:30:00'), (9, 'POST', 6, '2024-03-30 17:55:00'), (10, 'POST', 6, '2024-03-31 11:20:00'),

(1, 'POST', 7, '2024-03-30 16:00:00'), (2, 'POST', 7, '2024-03-31 09:30:00'), (4, 'POST', 7, '2024-03-31 13:15:00'),
(5, 'POST', 7, '2024-03-31 16:40:00'), (6, 'POST', 7, '2024-04-01 10:25:00'), (7, 'POST', 7, '2024-04-01 14:50:00'),

(2, 'POST', 8, '2024-04-02 23:00:00'), (4, 'POST', 8, '2024-04-03 08:45:00'), (5, 'POST', 8, '2024-04-03 12:30:00'),
(6, 'POST', 8, '2024-04-03 16:15:00'), (7, 'POST', 8, '2024-04-03 19:50:00'), (8, 'POST', 8, '2024-04-04 11:25:00'),

(1, 'POST', 9, '2024-04-05 17:00:00'), (2, 'POST', 9, '2024-04-06 09:30:00'), (4, 'POST', 9, '2024-04-06 13:45:00'),
(5, 'POST', 9, '2024-04-06 17:20:00'), (6, 'POST', 9, '2024-04-07 10:55:00'), (7, 'POST', 9, '2024-04-07 15:30:00'),

-- busan_lover의 게시물에 대한 좋아요
(1, 'POST', 10, '2024-04-08 11:00:00'), (2, 'POST', 10, '2024-04-08 14:30:00'), (3, 'POST', 10, '2024-04-08 17:45:00'),
(5, 'POST', 10, '2024-04-09 09:20:00'), (6, 'POST', 10, '2024-04-09 13:15:00'), (7, 'POST', 10, '2024-04-09 16:40:00'),
(8, 'POST', 10, '2024-04-09 19:25:00'), (9, 'POST', 10, '2024-04-10 11:50:00'), (10, 'POST', 10, '2024-04-10 15:35:00'),

(1, 'POST', 11, '2024-04-10 15:00:00'), (2, 'POST', 11, '2024-04-11 08:30:00'), (3, 'POST', 11, '2024-04-11 12:45:00'),
(5, 'POST', 11, '2024-04-11 16:20:00'), (6, 'POST', 11, '2024-04-11 19:55:00'),

-- mountain_hiker의 게시물에 대한 좋아요
(1, 'POST', 12, '2024-04-12 08:00:00'), (2, 'POST', 12, '2024-04-12 11:30:00'), (3, 'POST', 12, '2024-04-12 15:45:00'),
(4, 'POST', 12, '2024-04-12 18:20:00'), (6, 'POST', 12, '2024-04-13 09:55:00'), (7, 'POST', 12, '2024-04-13 13:30:00'),
(8, 'POST', 12, '2024-04-13 17:15:00'), (9, 'POST', 12, '2024-04-13 20:40:00'), (10, 'POST', 12, '2024-04-14 12:25:00'),

(1, 'POST', 13, '2024-04-15 07:00:00'), (2, 'POST', 13, '2024-04-15 10:45:00'), (3, 'POST', 13, '2024-04-15 14:30:00'),
(4, 'POST', 13, '2024-04-15 17:55:00'), (6, 'POST', 13, '2024-04-16 09:20:00'), (7, 'POST', 13, '2024-04-16 13:45:00'),

-- photo_artist의 게시물에 대한 좋아요 (인기 많음)
(1, 'POST', 19, '2024-05-08 20:00:00'), (2, 'POST', 19, '2024-05-08 21:30:00'), (3, 'POST', 19, '2024-05-09 08:15:00'),
(4, 'POST', 19, '2024-05-09 11:40:00'), (5, 'POST', 19, '2024-05-09 15:25:00'), (6, 'POST', 19, '2024-05-09 18:50:00'),
(7, 'POST', 19, '2024-05-09 21:15:00'), (8, 'POST', 19, '2024-05-10 09:40:00'), (10, 'POST', 19, '2024-05-10 13:25:00'),

(1, 'POST', 20, '2024-05-10 15:00:00'), (2, 'POST', 20, '2024-05-10 17:30:00'), (3, 'POST', 20, '2024-05-10 19:45:00'),
(4, 'POST', 20, '2024-05-11 08:20:00'), (5, 'POST', 20, '2024-05-11 12:55:00'), (6, 'POST', 20, '2024-05-11 16:30:00'),

-- student_explorer의 게시물에 대한 좋아요 (학생들에게 인기)
(1, 'POST', 23, '2024-05-18 16:00:00'), (2, 'POST', 23, '2024-05-18 18:45:00'), (3, 'POST', 23, '2024-05-18 21:30:00'),
(4, 'POST', 23, '2024-05-19 08:15:00'), (5, 'POST', 23, '2024-05-19 11:40:00'), (6, 'POST', 23, '2024-05-19 14:25:00'),
(7, 'POST', 23, '2024-05-19 17:50:00'), (8, 'POST', 23, '2024-05-19 20:35:00'), (9, 'POST', 23, '2024-05-20 09:20:00');

-- BLOB 댓글 좋아요 데이터 생성
INSERT INTO blob_likes (user_id, target_type, target_id, created_at) VALUES
-- 인기 댓글들에 대한 좋아요
(1, 'COMMENT', 1, '2024-03-15 13:30:00'), (3, 'COMMENT', 1, '2024-03-15 16:15:00'), (5, 'COMMENT', 1, '2024-03-16 10:45:00'),
(2, 'COMMENT', 2, '2024-03-15 15:20:00'), (4, 'COMMENT', 2, '2024-03-15 18:30:00'),
(3, 'COMMENT', 4, '2024-03-18 17:30:00'), (6, 'COMMENT', 4, '2024-03-19 11:20:00'), (8, 'COMMENT', 4, '2024-03-19 16:45:00'),
(1, 'COMMENT', 15, '2024-03-25 14:30:00'), (3, 'COMMENT', 15, '2024-03-25 17:20:00'), (5, 'COMMENT', 15, '2024-03-26 09:45:00'),
(2, 'COMMENT', 17, '2024-03-28 22:30:00'), (4, 'COMMENT', 17, '2024-03-29 10:15:00'), (6, 'COMMENT', 17, '2024-03-29 14:40:00'),
(1, 'COMMENT', 27, '2024-05-08 21:30:00'), (3, 'COMMENT', 27, '2024-05-09 09:45:00'), (5, 'COMMENT', 27, '2024-05-09 13:20:00');

-- BLOB 북마크 데이터 생성
INSERT INTO blob_bookmarks (user_id, post_id, created_at) VALUES
-- 여행 관련 게시물 북마크 (여행 정보는 나중에 참고하기 위해 많이 북마크됨)
(2, 1, '2024-03-15 14:00:00'), (3, 1, '2024-03-15 16:30:00'), (5, 1, '2024-03-16 11:20:00'),
(6, 1, '2024-03-17 10:45:00'), (8, 1, '2024-03-18 12:15:00'),

(3, 2, '2024-03-18 18:00:00'), (5, 2, '2024-03-19 13:30:00'), (7, 2, '2024-03-20 10:45:00'),
(9, 2, '2024-03-20 15:20:00'), (10, 2, '2024-03-21 11:35:00'),

(4, 3, '2024-03-20 13:00:00'), (6, 3, '2024-03-21 09:30:00'), (8, 3, '2024-03-21 16:45:00'),

-- 맛집 정보 북마크
(1, 5, '2024-03-25 14:00:00'), (3, 5, '2024-03-25 17:30:00'), (4, 5, '2024-03-26 10:20:00'),
(7, 5, '2024-03-26 14:45:00'), (9, 5, '2024-03-27 12:30:00'),

-- 유용한 정보들 북마크 (화장실, 약국, 교통 등)
(1, 7, '2024-03-30 17:00:00'), (4, 7, '2024-03-31 14:30:00'), (6, 7, '2024-04-01 11:45:00'),

(2, 8, '2024-04-02 23:30:00'), (5, 8, '2024-04-03 13:15:00'), (7, 8, '2024-04-03 17:40:00'),
(9, 8, '2024-04-04 12:25:00'), (10, 8, '2024-04-04 16:50:00'),

-- 등산 정보 북마크
(3, 12, '2024-04-12 16:00:00'), (6, 12, '2024-04-13 14:30:00'), (8, 12, '2024-04-13 18:45:00'),

-- 카페 정보 북마크
(2, 11, '2024-04-18 16:00:00'), (7, 11, '2024-04-18 19:30:00'), (9, 11, '2024-04-19 12:45:00'),

-- 사진 촬영 팁 북마크
(1, 19, '2024-05-08 21:00:00'), (3, 19, '2024-05-09 09:30:00'), (6, 19, '2024-05-09 15:45:00'),
(8, 19, '2024-05-10 11:20:00'),

(2, 20, '2024-05-10 16:00:00'), (4, 20, '2024-05-11 09:45:00'), (7, 20, '2024-05-11 14:30:00'),

-- 학생 여행 팁 북마크 (학생들과 여행 관심자들)
(1, 23, '2024-05-18 17:00:00'), (3, 23, '2024-05-18 22:30:00'), (5, 23, '2024-05-19 12:45:00'),
(7, 23, '2024-05-19 18:20:00'), (9, 23, '2024-05-20 10:35:00');

-- BLOB 알림 데이터 생성
INSERT INTO blob_notifications (user_id, related_id, type, title, content, is_read, created_at) VALUES
-- traveler_jane의 게시물에 대한 알림들 (좋아요, 댓글 알림)
(1, 1, 'POST_LIKE', '게시물 좋아요 알림', 'foodie_kim님이 회원님의 게시물을 좋아합니다.', false, '2024-03-15 13:00:00'),
(1, 1, 'NEW_COMMENT', '새 댓글 알림', 'foodie_kim님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-03-15 12:30:00'),
(1, 1, 'POST_LIKE', '게시물 좋아요 알림', 'seoul_walker님이 회원님의 게시물을 좋아합니다.', true, '2024-03-15 15:30:00'),
(1, 1, 'NEW_COMMENT', '새 댓글 알림', 'seoul_walker님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-03-15 14:20:00'),
(1, 2, 'POST_LIKE', '게시물 좋아요 알림', 'busan_lover님이 회원님의 게시물을 좋아합니다.', false, '2024-03-19 08:45:00'),
(1, 2, 'NEW_COMMENT', '새 댓글 알림', 'busan_lover님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-03-18 16:45:00'),

-- foodie_kim의 게시물에 대한 알림들
(2, 4, 'POST_LIKE', '게시물 좋아요 알림', 'traveler_jane님이 회원님의 게시물을 좋아합니다.', true, '2024-03-22 20:00:00'),
(2, 4, 'NEW_COMMENT', '새 댓글 알림', 'traveler_jane님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-03-22 19:40:00'),
(2, 5, 'POST_LIKE', '게시물 좋아요 알림', 'cafe_curator님이 회원님의 게시물을 좋아합니다.', false, '2024-03-26 12:15:00'),
(2, 5, 'NEW_COMMENT', '새 댓글 알림', 'cafe_curator님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-03-25 13:45:00'),

-- seoul_walker의 게시물에 대한 알림들
(3, 6, 'POST_LIKE', '게시물 좋아요 알림', 'traveler_jane님이 회원님의 게시물을 좋아합니다.', true, '2024-03-28 21:00:00'),
(3, 6, 'NEW_COMMENT', '새 댓글 알림', 'traveler_jane님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-03-28 21:45:00'),
(3, 7, 'NEW_COMMENT', '새 댓글 알림', 'local_guide님이 회원님의 게시물에 댓글을 남겼습니다.', false, '2024-04-02 23:00:00'),
(3, 8, 'POST_LIKE', '게시물 좋아요 알림', 'mountain_hiker님이 회원님의 게시물을 좋아합니다.', false, '2024-04-03 12:30:00'),

-- 추가 알림 데이터 (올바른 형식으로 작성된 샘플)
(4, 10, 'POST_LIKE', '게시물 좋아요 알림', 'traveler_jane님이 회원님의 게시물을 좋아합니다.', true, '2024-04-08 11:00:00'),
(5, 12, 'NEW_COMMENT', '새 댓글 알림', 'seoul_walker님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-04-12 08:30:00'),
(9, 19, 'POST_LIKE', '게시물 좋아요 알림', 'traveler_jane님이 회원님의 게시물을 좋아합니다.', true, '2024-05-08 20:00:00'),
(10, 23, 'NEW_COMMENT', '새 댓글 알림', 'traveler_jane님이 회원님의 게시물에 댓글을 남겼습니다.', true, '2024-05-18 16:45:00');

-- BLOB 데이터 초기화 완료
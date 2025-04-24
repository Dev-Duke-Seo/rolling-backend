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
import { useRef, useState } from "react";
import MyButton from "../button/MyButton";
import { useLocation, useNavigate } from "react-router-dom";

const JoinProfile = () => {

    //useRef를 이용해 input 태그 접근
    const imageInput = useRef();
    const [image, setImage] = useState("");
    const location = useLocation();
    const navigate = useNavigate();

    const nickname = location.state.nickname;
    const saveFileImage = (e) => {
        setImage(URL.createObjectURL(e.target.files[0]));
    }

    const onClickImageUpload = () => {
        imageInput.current.click();
    }

 return (
    <div className="member_wrapper">
        <div className='member_form'>
            <span style={{"fontSize":18}}> <b>프로필 사진으로 자신을 표현해보세요</b> </span>
        <br/>
        <br/>

            <div className ="image_wrapper">
                {image && (
                    <img alt="sample"
                    className="profile"
                    src={image}
                    style= {{ margin:"auto"}} />
                )}
            </div>
            <input type='file' name="imgUpload" style={{display:"none"}} ref={imageInput} onChange={saveFileImage} accept="image/*"/>
            <br/>
           <b>{nickname}</b>
        <br/>
        <br/>
            <MyButton onClick={onClickImageUpload} text={'이미지 업로드'}/>
        <br/>
        <br/>
            <MyButton type={'positive'} text={'몽땅 시작하기'} onClick={()=>navigate('/home/feed')}/>
        </div>
    </div>
 );
}

export default JoinProfile;
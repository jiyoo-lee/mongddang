import { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";


const Admin = () => {

    const [ members, setMembers ] = useState([]);
    const [ keyword, setKeyword ] = useState("");

    useEffect(()=>{
        GetAxios('/admin/members/',{params:{}},(res)=>{
            setMembers(res.data)
        })
    },[])    

    const navigate = useNavigate();

    const onDelete = (memberId) => {
        DeleteAxios('/admin/members',{data:{memberId: memberId}},(res)=>{
            alert("삭제 처리 되었습니다.")
            window.location.reload()})
    }

    const onChange = (e) =>{
        setKeyword(e.target.value)
    }

    const onSearch = () => {
        GetAxios('/admin/members/'+keyword,{params:{}},(res)=>{setMembers(res.data)})
    }

    return (
        <div className="admin">
        <ul className="admin_wrapper">
        <div className="logo_box">
        <img className='logo' src={process.env.PUBLIC_URL + `.././img/present_logo.png`} 
                 onClick={()=> {navigate('/admin')}} alt='logo'/>
                 </div>
                <li className="deth_menu"> Member </li>
                <li className="admin_menu" onClick={()=>window.location.reload()}> 회원 조회 </li>
                <hr/>
                <li className="deth_menu"> Painting </li>
                <li className="admin_menu" onClick={()=>navigate('/admin/paintings')} > 그림 조회 </li>
                <hr/>
                <li className="deth_menu"> Contest </li>
                <li className="admin_menu"> 공모전 관리 </li>
                <li className="admin_menu" onClick={()=>
                                                {sessionStorage.clear(); navigate('/'); }
                }> 로그아웃 </li>
        </ul>

<section className="notice">
  <div className="page-title">
        <div className="container">
            <h3>회원 조회</h3>
        </div>
    </div>
    <div id="board-search">
        <div className="container">
            <div className="search-window">
                    <div className="search-wrap">
                        <input type="text"  placeholder="회원 이름 혹은 아이디를 입력해주세요." value={keyword} onChange={(e)=>onChange(e)}/>
                        <button type="submit" className="btn btn-dark" onClick={onSearch}>검색</button>
                    </div>
            </div>
        </div>
    </div>
   
    <div id="board-list">
        <div className="container">
            <table className="board-table">
                <thead>
                <tr>
                    <th scope="col" className="th-num">아이디</th>
                    <th scope="col" className="th-num">이름</th>
                    <th scope="col" className="th-title">닉네임</th>
                    <th scope="col" className="th-date">휴대폰번호</th>
                    <th scope="col" className="th-date">이메일</th>
                    <th scope="col" className="th-date">마지막 로그인</th>
                    <th scope="col" className="th-date">삭제</th>
                </tr>
                </thead>
                <tbody>
                {members.length > 0 ? members.map(member => (
                    
                    <tr key={member.memberId}>
                        <td>{member.memberId}</td>
                        <td>
                        {member.name}
                        </td>
                        <td>{member.nickname}</td>
                        <td>{member.phoneNumber}</td>
                        <td>{member.email}</td>
                        <td>{member.lastAccessDatetime}</td>
                        <td onClick={()=>onDelete(member.memberId)}> 삭제 </td>
                </tr>
                   
                )): <></>}
          
                </tbody>
            </table>
        </div>
    </div>

</section>
        </div>
    );
}

export default Admin;